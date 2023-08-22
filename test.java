import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import java.util.List;

public class ListGcsBuckets {
  public static void listGcsBuckets(String googleAccessKeyId, String googleAccessKeySecret) {

    String googleAccessKeyId = "GOOG1EUKHLVYQYZOG44OSYFJBJBP6XUYKI5MT37ZPDYF7HQ5B7S7PDBSERFQK";
    String googleAccessKeySecret = "XUHJGIk8IP+MopTBoPkJPvv7bHSuWmrl4fVvotyN";

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
