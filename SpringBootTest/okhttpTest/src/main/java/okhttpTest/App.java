package okhttpTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * nothing
 *
 * @ClassName App
 * @Description
 * @Author zsks
 * @Date 2021/11/9 22:05
 * @Version 1.0
 **/
@SpringBootApplication
@EnableCaching
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
