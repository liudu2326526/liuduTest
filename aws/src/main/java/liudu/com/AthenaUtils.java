package liudu.com;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.athena.AmazonAthena;
import com.amazonaws.services.athena.AmazonAthenaClientBuilder;
import com.amazonaws.services.athena.model.GetQueryExecutionRequest;
import com.amazonaws.services.athena.model.GetQueryExecutionResult;
import com.amazonaws.services.athena.model.GetQueryResultsRequest;
import com.amazonaws.services.athena.model.GetQueryResultsResult;
import com.amazonaws.services.athena.model.QueryExecutionContext;
import com.amazonaws.services.athena.model.QueryExecutionState;
import com.amazonaws.services.athena.model.ResultConfiguration;
import com.amazonaws.services.athena.model.Row;
import com.amazonaws.services.athena.model.StartQueryExecutionRequest;
import com.amazonaws.services.athena.model.StartQueryExecutionResult;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Liu Du.
 */
public class AthenaUtils {

  private static final String RESULT_DIR = "s3://aws-athena-query-results-130132914922-us-east-1/";

  private static final String SQL_COUNT = "SELECT count(*) FROM "
      + "ods.ods_user_mixed_crowd_package_inc_be WHERE type = '%s' AND name = '%s'";

  private static final ResultConfiguration resultConfiguration = new ResultConfiguration()
      .withOutputLocation(RESULT_DIR);

  private static AmazonAthena amazonAthena;

  static {
    amazonAthena = AmazonAthenaClientBuilder.standard()
        .withRegion(Regions.US_EAST_1)
        .withCredentials(new DefaultAWSCredentialsProviderChain())
        .build();
  }

  public static String addUserPackagePartition(String type, String name) {
    String path = String.format(
        "s3://ad-management/prod/data/ods/mixed/ods_user_mixed_crowd_package_inc_be/type=%s/name=%s/",
        type, name);
    String sql = String.format(
        "ALTER TABLE ods.ods_user_mixed_crowd_package_inc_be "
            + "ADD IF NOT EXISTS PARTITION(type='%s', name='%s') LOCATION '%s'",
        type, name, path);
    return submitAthenaQuery(sql, "ods");
  }

  public static Long getPackageCount(String type, String name) throws InterruptedException {
    return Long.parseLong(querySQL(String.format(SQL_COUNT, type, name), "ods"));
  }

  public static String querySQL(String sql, String database) throws InterruptedException {

    String queryExecutionId = submitAthenaQuery(sql, database);

    waitForQueryToComplete(amazonAthena, queryExecutionId);

    String value = processResultRows(amazonAthena, queryExecutionId);

    if (!NumberUtils.isNumber(value)
        || BigDecimal.valueOf(Double.parseDouble(value)).compareTo(BigDecimal.valueOf(0)) <= 0) {
      throw new RuntimeException("query value is not valid:" + value);
    }
    return value;
  }

  public static String submitAthenaQuery(String sql, String database) {
    QueryExecutionContext queryExecutionContext = new QueryExecutionContext()
        .withDatabase(database);

    StartQueryExecutionRequest startQueryExecutionRequest = new StartQueryExecutionRequest()
        .withQueryString(sql)
        .withQueryExecutionContext(queryExecutionContext)
        .withResultConfiguration(resultConfiguration);

    StartQueryExecutionResult startQueryExecutionResult = amazonAthena
        .startQueryExecution(startQueryExecutionRequest);
    return startQueryExecutionResult.getQueryExecutionId();
  }

  private static void waitForQueryToComplete(AmazonAthena amazonAthena, String queryExecutionId)
      throws InterruptedException {
    GetQueryExecutionRequest getQueryExecutionRequest = new GetQueryExecutionRequest()
        .withQueryExecutionId(queryExecutionId);

    GetQueryExecutionResult queryExecution;
    boolean isQueryStillRunning = true;
    while (isQueryStillRunning) {
      queryExecution = amazonAthena.getQueryExecution(getQueryExecutionRequest);
      String queryState = queryExecution
          .getQueryExecution()
          .getStatus()
          .getState();

      if (queryState.equals(QueryExecutionState.FAILED.toString())) {
        throw new RuntimeException("Query Failed to run with Error Message: "
            + queryExecution.getQueryExecution().getStatus().getStateChangeReason());
      } else if (queryState.equals(QueryExecutionState.CANCELLED.toString())) {
        throw new RuntimeException("Query was cancelled.");
      } else if (queryState.equals(QueryExecutionState.SUCCEEDED.toString())) {
        isQueryStillRunning = false;
      } else {
        Thread.sleep(1000);
      }
    }
  }

  private static String processResultRows(AmazonAthena amazonAthena, String queryExecutionId) {
    GetQueryResultsRequest getQueryResultsRequest = new GetQueryResultsRequest()
        .withQueryExecutionId(queryExecutionId);

    GetQueryResultsResult queryResults = amazonAthena.getQueryResults(getQueryResultsRequest);
    Row row = queryResults
        .getResultSet()
        .getRows()
        .get(1);

    return row.getData()
        .get(0)
        .getVarCharValue();
  }

}
