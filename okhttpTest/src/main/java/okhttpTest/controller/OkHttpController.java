package okhttpTest.controller;

import okhttp3.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * nothing
 *
 * @ClassName OkHttpController
 * @Description
 * @Author zsks
 * @Date 2021/11/21 18:21
 * @Version 1.0
 **/
@RestController
@RequestMapping("/okhttp")
public class OkHttpController {

    /**
     *  okhttp的cache使用，感觉没啥用先不写了
     * @Author 只是开始
     * @param
     * @return void
     **/
    @RequestMapping("/test")
    public void test3(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }

//    @RequestMapping("/test")
    public void test(){
        OkHttpClient client = new OkHttpClient().newBuilder()
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        client.dispatcher().setMaxRequests(2);
        client.dispatcher().setMaxRequestsPerHost(1);
        for(int i=0; i<5; i++){
            new Thread(()->{
                /*
                client.newCall(new Request.Builder()
                .url("http://localhost:8080/okhttp/receive")
                .build()).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("失败了....");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println("成功了~~~");
                    }
                });
                // 这是异步请求的用法，Callback是okhttp自己的线程
                 */
                try {
                    // okhttp同步请求的用法
                    client.newCall(new Request.Builder()
                    .url("http://localhost:8080/okhttp/receive")
                    .build()).execute();
                    System.out.println("成功了"+new Date());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
    @RequestMapping("/receive")
    public void test2() throws InterruptedException {
        Thread.sleep(2000);
    }
}
