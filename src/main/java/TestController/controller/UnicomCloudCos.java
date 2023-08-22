package TestController.controller;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

/**
 * 联通云 cos使用
 * @author zhg
 * @date 2023/7/27
 */
public class UnicomCloudCos {

    static String bucketName = "bonc-test";
    static String accessKey = "ALN8OVAFV4404Z0J7YU1";
    static String secretKey = "KZA19vD4TY3OOR0v9PUQRS3nSohmxvQAzrR3TYBK";
//    static String serviceEndpoint = "https://cos.hhht-hqc.cos.tg.unicom.local/939984502447:bonc-test"; // e.g., "cos.gz-tst.cos.tg.unicom.local"
//    static String serviceEndpoint = "http://cos.hhht-hqc.cos.tg.unicom.local";
    static String serviceEndpoint = "http://cos.hhht-hqc.cos.tg.unicom.local";
    static String region = "bonc-test"; // e.g., "gz-tst"

    static AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
    static AWSStaticCredentialsProvider awsStaticCredentialsProvider = new AWSStaticCredentialsProvider(credentials);
    static ClientConfiguration config = new ClientConfiguration();
    static AwsClientBuilder.EndpointConfiguration endpointConfiguration = new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, region);
    static AmazonS3 conn = AmazonS3ClientBuilder.standard()
            .withCredentials(awsStaticCredentialsProvider)
            .withClientConfiguration(config.withProtocol(Protocol.HTTP).withSignerOverride("S3SignerType"))
            .withEndpointConfiguration(endpointConfiguration).build();

    public static void main(String[] args) {
        listBuckets();
//        uploadFile();
//        getFileUrl();
//        getSignificantUrl();
    }
    //   可以访问
    public static void listBuckets(){
        List<Bucket> buckets = conn.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName() + "\t" + StringUtils.fromDate(bucket.getCreationDate()));
        }
    }

    public static void uploadFile(){
        ByteArrayInputStream input = new ByteArrayInputStream("Hello World!".getBytes());
        conn.putObject(bucketName, "hello.txt", input, new ObjectMetadata());
    }
    // 访问不了
    public static void getFileUrl(){
        URL url = conn.getUrl(bucketName, "hello.txt");
        System.out.println(url.toString());
    }
    public static void getSignificantUrl(){
        Date date = Date.from(Instant.now().plus(10, ChronoUnit.MINUTES));
        URL url = conn.generatePresignedUrl(bucketName, "hello.txt", date);
        System.out.println(url.toString());
    }
}
