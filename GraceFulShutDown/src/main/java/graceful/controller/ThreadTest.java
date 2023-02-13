package graceful.controller;

import org.springframework.scheduling.concurrent.ThreadPoolExecutorFactoryBean;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadTest
 * @Description
 * @Author zsks
 * @Date 2022/2/10 20:12
 * @Version 1.0
 **/
public class ThreadTest {

    private static ThreadPoolExecutor executor = getThreadPool();
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                executor.submit(()->{
                    System.out.println(new Date());
                });
                if(executor.isShutdown()){
                    System.out.println("executor is closed");
                }
            }
        }).start();
        Thread.sleep(2000);
        executor.shutdown();
        Thread.sleep(100000000);
    }

    public static ThreadPoolExecutor getThreadPool(){
        return new ThreadPoolExecutor(1, 3, 1000, TimeUnit.MILLISECONDS
                , new LinkedBlockingQueue<>(8), new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
