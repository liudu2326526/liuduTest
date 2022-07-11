package com.liudu.calcite;

import java.util.HashSet;
import java.util.Set;
import org.apache.calcite.sql.SqlCall;
import org.apache.calcite.sql.SqlIdentifier;
import org.apache.calcite.sql.SqlInsert;
import org.apache.calcite.sql.SqlJoin;
import org.apache.calcite.sql.SqlKind;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlSelect;
import org.apache.calcite.sql.parser.SqlParser;

/**
 * @author liudu
 * @title: CalciteTest
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/7/6下午4:56
 */
public class CalciteTest {

  public static SqlNode parseStatement(String sql) {
    SqlParser parser = SqlParser.create(sql);
    try {
      return parser.parseQuery();
    } catch (Exception e) {
      e.printStackTrace();
      throw new UnsupportedOperationException("operation not allowed");
    }
  }


  private static Set<String> extractSourceTableInInsertSql(SqlNode sqlNode, boolean fromOrJoin) {
    SqlInsert sqlInsert = (SqlInsert) sqlNode;
    Set<String> insertList = new HashSet<>(
        extractSourceTableInSelectSql(sqlInsert.getSource(), false));
    final SqlNode targetTable = sqlInsert.getTargetTable();
    if (targetTable instanceof SqlIdentifier) {
      insertList.add(((SqlIdentifier) targetTable).toString());
    }
    return insertList;
  }

  private static Set<String> extractSourceTableInSelectSql(SqlNode sqlNode, boolean fromOrJoin) {
    if (sqlNode == null) {
      return new HashSet<>();
    }
    try {
      System.out.println(sqlNode);
    } catch (Exception e){
      System.out.println("------------");
    }

    final SqlKind sqlKind = sqlNode.getKind();
    System.out.println(sqlKind);
    if (SqlKind.SELECT.equals(sqlKind)) {
      SqlSelect selectNode = (SqlSelect) sqlNode;
      Set<String> selectList = new HashSet<>(
          extractSourceTableInSelectSql(selectNode.getFrom(), true));
      selectNode.getSelectList().getList().stream().filter(node -> node instanceof SqlCall)
          .forEach(node -> selectList.addAll(extractSourceTableInSelectSql(node, false)));
      selectList.addAll(extractSourceTableInSelectSql(selectNode.getWhere(), false));
      selectList.addAll(extractSourceTableInSelectSql(selectNode.getHaving(), false));
      return selectList;
    }
    if (SqlKind.JOIN.equals(sqlKind)) {
      SqlJoin sqlJoin = (SqlJoin) sqlNode;
      Set<String> joinList = new HashSet<>();
      joinList.addAll(extractSourceTableInSelectSql(sqlJoin.getLeft(), true));
      joinList.addAll(extractSourceTableInSelectSql(sqlJoin.getRight(), true));
      return joinList;
    }
    if (SqlKind.AS.equals(sqlKind)) {
      SqlCall sqlCall = (SqlCall) sqlNode;
      return extractSourceTableInSelectSql(sqlCall.getOperandList().get(0), fromOrJoin);
    }
    if (SqlKind.IDENTIFIER.equals(sqlKind)) {
      Set<String> identifierList = new HashSet<>();
      if (fromOrJoin) {
        SqlIdentifier sqlIdentifier = (SqlIdentifier) sqlNode;
        identifierList.add(sqlIdentifier.toString());
      }
      return identifierList;
    }
    Set<String> defaultList = new HashSet<>();
    if (sqlNode instanceof SqlCall) {
      SqlCall call = (SqlCall) sqlNode;
      call.getOperandList()
          .forEach(node -> defaultList.addAll(extractSourceTableInSelectSql(node, false)));
      return defaultList;
    }
    return new HashSet<>();
  }

  private static final String sql0 = "SELECT MIN(relation_id) FROM tableA JOIN TableB  GROUP BY account_instance_id, follow_account_instance_id HAVING COUNT(*)>1";

  private static final String sql1 = "SELECT * FROM blog_user_relation a WHERE (a.account_instance_id,a.follow_account_instance_id) IN (SELECT account_instance_id,follow_account_instance_id FROM Blogs_info GROUP BY account_instance_id, follow_account_instance_id HAVING COUNT(*) > 1)";
  private static final String sql2 = "select name from (select * from student)";
  private static final String sql3 =
      "SELECT * FROM Student LEFT JOIN Grade ON Student.sID = Grade.gID\n" +
          "UNION\n" +
          "SELECT * FROM Student RIGHT JOIN Grade ON Student.sID = Grade.gID";
  private static final String sql4 = "SELECT *\n" +
      "FROM teacher\n" +
      "WHERE birth = (SELECT MIN(birth)\n" +
      "               FROM employee)";
  private static final String sql5 = "SELECT sName\n" +
      "FROM Student\n" +
      "WHERE '450' NOT IN (SELECT courseID\n" +
      "                    FROM Course\n" +
      "                    WHERE sID = Student.sID)";

  private static final String sql6 = "        INSERT INTO dm.dm_athena_mixed_adx_supply_metrics_inc_hourly\n"
      + "        SELECT \n"
      + "            supplyId supply_id\n"
      + "          , bundle\n"
      + "          , sPlacement supply_placement\n"
      + "          , country\n"
      + "          , version app_version\n"
      + "          , COUNT(DISTINCT IF(supplyId IS NOT NULL, globalId, NULL)) AS supply_request\n"
      + "          , COUNT(DISTINCT IF(supply_bid_price IS NOT NULL, globalId, NULL)) AS supply_bid\n"
      + "          , CAST(SUM(supply_bid_price) AS DECIMAL(18, 6)) AS supply_bid_price\n"
      + "          , SUM(IF(supply_bid_price IS NOT NULL, 1, 0 )) supply_bid_price_cnt\n"
      + "          , COUNT(DISTINCT IF(supply_win_price IS NOT NULL, globalId, NULL)) AS supply_win\n"
      + "          , CAST(SUM(supply_win_price) AS DECIMAL(18, 6)) AS supply_win_price\n"
      + "          , SUM(IF(supply_win_price IS NOT NULL, 1, 0 )) supply_win_price_cnt\n"
      + "          , SUM(IF(is_valid_impression AND win, 1, 0)) AS impression_cnt\n"
      + "          , SUM(IF(is_valid_impression AND win AND click = 1, 1, 0)) AS click_cnt\n"
      + "          , CAST(SUM(IF(is_valid_impression AND win, CAST(demandcontext_price AS DECIMAL(18, 6)), 0.0)) AS DECIMAL(18, 6)) AS revenue\n"
      + "          , CAST(SUM(IF(is_valid_impression AND win, CAST(supply_win_price AS DECIMAL(18, 6)), 0.0)) AS DECIMAL(18, 6)) AS cost\n"
      + "          , SUM(IF(supply_exception IS NOT NULL, 1, 0)) supply_error\n"
      + "          , dt\n"
      + "          , hour_pk\n"
      + "        FROM ( \n"
      + "              SELECT \n"
      + "                  globalId\n"
      + "                , bidId\n"
      + "                , supplycontext.supplyId AS supplyId\n"
      + "                , supplycontext.appContext.bundle AS bundle\n"
      + "                , supplycontext.sPlacement AS sPlacement\n"
      + "                , supplycontext.appContext.version AS version\n"
      + "                , supplycontext.deviceContext.geo.country AS country\n"
      + "                , supplycontext.bidPrice AS supply_bid_price\n"
      + "                , supplycontext.winPrice AS supply_win_price\n"
      + "                , is_valid_impression\n"
      + "                , impression\n"
      + "                , click\n"
      + "                , dt\n"
      + "                , hour_pk\n"
      + "                , demandCont.win AS win\n"
      + "                , demandCont.adQuery.response.responseBody.price AS demandcontext_price\n"
      + "                , exception AS supply_exception\n"
      + "              FROM dwd.dwd_ad_mixed_adx_core_event_inc_hourly, UNNEST(demandContexts) AS t(demandCont)\n"
      + "              WHERE \n"
      + "                    dt = '{dt_filter}'\n"
      + "                AND hour_pk = '{hour_filter}'\n"
      + "        ) t \n"
      + "        GROUP BY \n"
      + "            1, 2, 3, 4, 5, 18, 19";

  public static void main(String[] args) {

    final SqlNode sqlNode0 = parseStatement(sql6);
    System.out.println("sqlNode0: " + extractSourceTableInSelectSql(sqlNode0, false));
  }
}
