package miniotest.controller;

import io.minio.MinioClient;
import io.minio.Result;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import miniotest.config.MinioAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author zhg
 * @date 2023/8/25
 */
@Slf4j
public class MinioController {

    private static MinioClient client;

    static{
        try {
            MinioAutoConfiguration config = new MinioAutoConfiguration();
//            config.setEndpoint("10.177.86.21");
            config.setEndpoint("10.177.76.193");
            config.setPort(9009);
            config.setAccessKey("admin");
            config.setSecretKey("Bonc@!QAZ2wsx");
            config.setSecure(false);
            config.setBucketName("yypt");
            client = config.minioClient();
        }catch (Exception e){
            log.error("initMinio Error", e);
        }
    }

    public static void main(String[] args) throws Exception {
        listBuckets();
    }

    public static void getPreSignedUrl() throws Exception {
        Iterable<Result<Item>> objects = client.listObjects("yypt");
        for (Result<Item> itemResult : objects) {
            String yypt = client.getPresignedObjectUrl(Method.GET, "yypt", itemResult.get().objectName(), 3600, null);
            System.out.println(yypt);
        }
    }

    public static void listObjects() throws Exception{
        Iterable<Result<Item>> objects = client.listObjects("yypt");
        for (Result<Item> itemResult : objects) {
            System.out.println(itemResult.get().objectName());
        }
    }

    public static void listBuckets() throws Exception{
        List<Bucket> buckets = client.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.name());
        }
    }

}
