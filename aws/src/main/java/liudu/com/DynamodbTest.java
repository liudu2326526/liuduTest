package liudu.com;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.DescribeTableResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughputDescription;

/**
 * @author liudu
 * @title: DynamodbTest
 * @projectName liuduTest
 * @description: TODO
 * @date 2022/1/6下午1:52
 */
public class DynamodbTest {

  public static void main(String[] args) {

    long writeCapacity = 1000l;

    AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.US_EAST_1)
        .withCredentials(new DefaultAWSCredentialsProviderChain())
        .build();

    ProvisionedThroughputDescription adsProfileStaging = dynamoDB.describeTable("AdsProfileStaging")
        .getTable().getProvisionedThroughput();

//    System.out.println(adsProfileStaging.getReadCapacityUnits());
    System.out.println(adsProfileStaging.getWriteCapacityUnits());

    adsProfileStaging.setWriteCapacityUnits(writeCapacity);

    System.out.println(adsProfileStaging.getWriteCapacityUnits());
  }

}
