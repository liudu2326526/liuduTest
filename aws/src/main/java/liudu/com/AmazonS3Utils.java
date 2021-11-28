package liudu.com;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;


public class AmazonS3Utils {

  private static AmazonS3 s3;

  private static final String USER_PACKAGE_PATH =
      "prod/data/ods/mixed/ods_user_mixed_crowd_package_inc_be/type=%s/name=%s/package.csv";

  private static final String S3_BUCKET = "ad-management";

  private static AmazonS3 initAmazonS3() {

    s3 = AmazonS3ClientBuilder.standard()
        .withRegion(Regions.US_EAST_1)
        .withCredentials(new DefaultAWSCredentialsProviderChain())
        .build();
    return s3;
  }

  public static boolean uploadToS3(String bucketName,
      File tempFile,
      String remoteFileName) {
    try {
      AmazonS3 s3 = getAmazonS3Client();
      if (!s3.doesBucketExist(bucketName)) {
        s3.createBucket(bucketName);
      }
      s3.putObject(new PutObjectRequest(bucketName, remoteFileName, tempFile)
          .withCannedAcl(CannedAccessControlList.PublicReadWrite));
      return true;
    } catch (Exception ase) {
//      log.error("amazonS3 failed to upload file " + ase.getMessage(), ase);
    }
    return false;
  }

  public static boolean uploadUserPackage(String type, String name, File tempFile) {
    String key = String.format(USER_PACKAGE_PATH, type, name);
    if (uploadToS3(S3_BUCKET, tempFile, key)) {
      AthenaUtils.addUserPackagePartition(type, name);
      return true;
    }
    return false;
  }

  public static boolean uploadToS3(String bucketName,
      String content,
      String remoteFileName) {
    ByteArrayInputStream inputStream = null;
    try {
      AmazonS3 s3 = getAmazonS3Client();
      if (!s3.doesBucketExist(bucketName)) {
        s3.createBucket(bucketName);
      }
      inputStream = new ByteArrayInputStream(content.getBytes());
      ObjectMetadata objectMetadata = new ObjectMetadata();
      objectMetadata.setContentLength(content.length());
      s3.putObject(new PutObjectRequest(bucketName, remoteFileName, inputStream, objectMetadata)
          .withCannedAcl(CannedAccessControlList.PublicReadWrite));
      return true;
    } catch (Exception ase) {
//      log.error("amazonS3 failed to upload file " + ase.getMessage(), ase);
    } finally {
      try {
        if (inputStream != null) {
          inputStream.close();
        }
      } catch (IOException e) {
//        log.error("upload to s3 error", e);
      }
    }
    return false;
  }

  public static boolean downloadFromS3(String bucketName, String remoteFileName, String path) {
    try {
      AmazonS3 s3 = getAmazonS3Client();

      GetObjectRequest request = new GetObjectRequest(bucketName, remoteFileName);
      s3.getObject(request, new File(path));
      return true;
    } catch (Exception ase) {
//      log.error("amazonS3 download file failed " + ase.getMessage(), ase);
    }
    return false;
  }


  public static void deleteFromS3(String bucketName, String remoteFileName) {
    AmazonS3 s3 = getAmazonS3Client();
    s3.deleteObject(bucketName, remoteFileName);
  }

  public static AmazonS3 getAmazonS3Client() {
    if (s3 == null) {
      return initAmazonS3();
    } else {
      return s3;
    }
  }


}
