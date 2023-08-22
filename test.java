import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import java.util.List;

public class ListGcsBuckets {
  public static void listGcsBuckets(String googleAccessKeyId, String googleAccessKeySecret) {

    String googleAccessKeyId = "GOOG1EQZ34MKM5NTMJ2TCZXXRYSB43SME4UMZYVX57WX5J73CSVDJ5RVUC72Y";
    String googleAccessKeySecret = "c+rZHiIPwEXaflHldZoiGCCUOZlDam9rzKdAFSQ3";

    // Create a BasicAWSCredentials using Cloud Storage HMAC credentials.
    BasicAWSCredentials googleCreds =
        new BasicAWSCredentials(googleAccessKeyId, googleAccessKeySecret);

    // Create a new client and do the following:
    // 1. Change the endpoint URL to use the Google Cloud Storage XML API endpoint.
    // 2. Use Cloud Storage HMAC Credentials.
    AmazonS3 interopClient =
        AmazonS3ClientBuilder.standard()
            .withEndpointConfiguration(
                new AwsClientBuilder.EndpointConfiguration(
                    "https://storage.googleapis.com", "auto"))
            .withCredentials(new AWSStaticCredentialsProvider(googleCreds))
            .build();

    // Call GCS to list current buckets
    List<Bucket> buckets = interopClient.listBuckets();

    // Print bucket names
    System.out.println("Buckets:");
    for (Bucket bucket : buckets) {
      System.out.println(bucket.getName());
    }

    // Explicitly clean up client resources.
    interopClient.shutdown();
  }
