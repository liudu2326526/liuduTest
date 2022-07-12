package com.liudu.neo4j;


import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;

/**
 * @author liudu
 * @title: App
 * @projectName liuduTest
 * @date 2022/7/12下午3:41
 */
public class App {

  public static void main(String[] args) {
    Driver driver = GraphDatabase
        .driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "123456"));
    Session session = driver.session();
//    session.run( "CREATE (a:Person {name: {name}, title: {title}})",
//        parameters( "name", "Arthur001", "title", "King001" ) );

    Result result = session.run("MATCH (a:Person)-[:BORN_IN]->(b:Location {city:'Boston'}) RETURN a,b");
    while (result.hasNext()) {
      Record record = result.next();
      System.out.println(record);
      Value a = record.get("a");
      System.out.println(a.get("name"));
      Value b = record.get("b");
      System.out.println(b.get("city"));
    }
    session.close();
    driver.close();
  }
}