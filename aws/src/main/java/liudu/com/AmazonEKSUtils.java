package liudu.com;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.emrcontainers.AmazonEMRContainers;
import com.amazonaws.services.emrcontainers.AmazonEMRContainersClientBuilder;
import com.amazonaws.services.emrcontainers.model.CloudWatchMonitoringConfiguration;
import com.amazonaws.services.emrcontainers.model.Configuration;
import com.amazonaws.services.emrcontainers.model.ConfigurationOverrides;
import com.amazonaws.services.emrcontainers.model.DescribeJobRunRequest;
import com.amazonaws.services.emrcontainers.model.DescribeJobRunResult;
import com.amazonaws.services.emrcontainers.model.DescribeVirtualClusterRequest;
import com.amazonaws.services.emrcontainers.model.JobDriver;
import com.amazonaws.services.emrcontainers.model.MonitoringConfiguration;
import com.amazonaws.services.emrcontainers.model.S3MonitoringConfiguration;
import com.amazonaws.services.emrcontainers.model.SparkSubmitJobDriver;
import com.amazonaws.services.emrcontainers.model.StartJobRunRequest;
import com.amazonaws.services.emrcontainers.model.StartJobRunResult;
import com.amazonaws.services.s3.AmazonS3;
import java.util.HashMap;

/**
 * @author Liu Du
 */
public class AmazonEKSUtils {

  private static AmazonEMRContainers eks;

  private static final String EKS_CLUSTER_ID = "eczhq648oqd6lijrjmsulxpp1";

  private static final String STRP_NAME = "CROWD-PACKAGE-ETL";

  private static final String S3_BUCKET = "dywx-data";

  private static final String SQL_PATH_PREFIX_KEY = "tmp/eks-sql/env=prod/";

  private static final String OUTPUT_PATH =
      "s3://dywx-data/prod/data/ods/mixed/ods_user_mixed_crowd_package_inc_be/type=udid/name=";

  private static AmazonEMRContainers initAmazonEMR() {

    eks = AmazonEMRContainersClientBuilder.standard()
        .withRegion(Regions.US_EAST_1)
        .withCredentials(new DefaultAWSCredentialsProviderChain())
        .build();
    eks.describeVirtualCluster(new DescribeVirtualClusterRequest().withId(EKS_CLUSTER_ID));
    return eks;
  }

  public static AmazonEMRContainers getAmazonEMRClient() {
    if (eks == null) {
      return initAmazonEMR();
    } else {
      return eks;
    }
  }

  public static Boolean waitForStep(String stepId, Integer timeout) throws InterruptedException {
    if (timeout < 0) {
      return false;
    }
    for (int i = 0; i < timeout; i++) {
//      log.info("checking step:{} state", stepId);
      DescribeJobRunResult result = null;
      try {
        result = getAmazonEMRClient()
            .describeJobRun(new DescribeJobRunRequest()
                .withId(stepId)
                .withVirtualClusterId(EKS_CLUSTER_ID));
      } catch (Exception e) {
//        log.warn("check step error: " + e.getMessage());
        Thread.sleep(60000);
        continue;
      }
      String state = result.getJobRun().getState();
//      log.info("step:{} in state:{}", stepId, state);
      if ("COMPLETED".equals(state)) {
        return true;
      } else if ("CANCELLED".equals(state) ||
          "FAILED".equals(state) ||
          "INTERRUPTED".equals(state)) {
//        log.error("step:{} fails in state:{}", stepId, state);
        return false;
      }
      Thread.sleep(60000);
    }
//    log.error("step:{} check timeout", stepId);
    return false;
  }

  public static Boolean transformGaidToUdid(String name) throws InterruptedException {

    AmazonS3 amazonS3Client = AmazonS3Utils.getAmazonS3Client();

    String date = DateUtils.getCurrDate();
    do {
      date = DateUtils.getOffsetDate(date, -1);
    } while (!amazonS3Client.doesObjectExist("dywx-data",
        String.format("prod/data/dwd/dwd_ad_user_detail_ss_daily_his/dt=%s/_SUCCESS", date)));

    String sql = String.format("SELECT B.udid FROM(\n"
        + "SELECT udid, gaid FROM dwd.dwd_ad_user_detail_ss_daily_his WHERE dt='%s' ) A \n"
        + "JOIN ods.ods_user_mixed_crowd_package_inc_be B \n"
        + "WHERE A.gaid = B.id AND B.type = 'gaid' AND B.name = '%s'", date, name);

    return sendJob(name, OUTPUT_PATH + name + "/", sql);
  }

  public static Boolean sendJob(String name, String output, String sql) {

    String sqlPath = "s3://" + S3_BUCKET + "/" + SQL_PATH_PREFIX_KEY + name + ".sql";

    AmazonS3Utils.uploadToS3(S3_BUCKET, sql, SQL_PATH_PREFIX_KEY + name + ".sql");

    SparkSubmitJobDriver sparkSubmitJobDriver = new SparkSubmitJobDriver()
        .withEntryPoint("s3://dywx-data/app/bigdata-etl-1.0.0.jar")
        .withEntryPointArguments("-o", output, "-p", "1", "-f",
            "sqlPath==" + sqlPath + "~~output_format==csv", "-n", "ETL-ACTIVITY-CONSUME")
        .withSparkSubmitParameters(
            " --deploy-mode cluster "
                + "--conf spark.network.timeout=600 "
                + "--conf spark.hadoop.orc.overwrite.output.file=true "
                + "--conf spark.executor.defaultJavaOptions=\"-verbose:gc -XX:+UseG1GC -Dfile.encoding=utf-8\" "
                + "--conf spark.driver.defaultJavaOptions=\"-verbose:gc -XX:+UseG1GC -Dfile.encoding=utf-8\" "
                + "--conf spark.serializer=org.apache.spark.serializer.KryoSerializer "
                + "--conf spark.shuffle.file.buffer=64k "
                + "--conf spark.kubernetes.memoryOverheadFactor=0.3 "
                + "--conf spark.kryo.unsafe=true "
                + "--conf spark.sql.adaptive.enabled=true "
                + "--conf spark.kubernetes.node.selector.eks.amazonaws.com/nodegroup=worker-c "
                + "--conf spark.kubernetes.executor.limit.cores=7 "
                + "--conf spark.kubernetes.executor.request.cores=7 "
                + "--conf spark.kubernetes.driver.limit.cores=1 "
                + "--conf spark.kubernetes.driver.request.cores=1 "
                + "--conf spark.driver.memory=2g "
                + "--conf spark.executor.memory=9924m "
                + "--conf spark.executor.instances=8 "
                + "--conf spark.executor.cores=14 "
                + "--conf spark.driver.cores=1 "
                + "--class com.dayuwuxian.em.recommend.etl.processor.QueryProcessor");

    HashMap<String, String> properties = new HashMap<>();
    properties.put("hive.metastore.client.factory.class",
        "com.amazonaws.glue.catalog.metastore.AWSGlueDataCatalogHiveClientFactory");

    Configuration applicationConfiguration = new Configuration()
        .withClassification("spark-hive-site")
        .withProperties(properties)
        .withConfigurations();

    MonitoringConfiguration monitoringConfiguration = new MonitoringConfiguration()
        .withPersistentAppUI("ENABLED")
        .withCloudWatchMonitoringConfiguration(new CloudWatchMonitoringConfiguration()
            .withLogGroupName("emr-eks")
            .withLogStreamNamePrefix("containers"))
        .withS3MonitoringConfiguration(new S3MonitoringConfiguration()
            .withLogUri("s3://dywx-recommend/emr-log/"));

    ConfigurationOverrides configurationOverrides = new ConfigurationOverrides()
        .withApplicationConfiguration(applicationConfiguration)
        .withMonitoringConfiguration(monitoringConfiguration);

    HashMap<String, String> tags = new HashMap<>();
    tags.put("Application:name", STRP_NAME);
    tags.put("Application:product", "Snaptube");
    tags.put("Owner:name", "liudu");
    tags.put("Application:func", "DATA");
    tags.put("Application:module", "Etl");
    tags.put("Owner:team", "Monetization");
    tags.put("Application:env", "prod");

    AmazonEMRContainers emr = getAmazonEMRClient();
    StartJobRunResult result = emr.startJobRun(
        new StartJobRunRequest()
            .withName(STRP_NAME)
            .withVirtualClusterId(EKS_CLUSTER_ID)
            .withExecutionRoleArn("arn:aws:iam::130132914922:role/MobiuspaceEMREKSRole")
            .withReleaseLabel("emr-6.3.0-latest")
            .withJobDriver(new JobDriver().withSparkSubmitJobDriver(sparkSubmitJobDriver))
            .withConfigurationOverrides(configurationOverrides)
            .withTags(tags));
    String resultId = result.getId();

    try {
      if (waitForStep(resultId, 60)) {
        AthenaUtils.addUserPackagePartition("udid", name);
        return true;
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return false;
  }

}
