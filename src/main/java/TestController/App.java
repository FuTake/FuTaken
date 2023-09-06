package TestController;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * nothing
 *
 * @ClassName App
 * @Description
 * @Author zsks
 * @Date 2021/12/20 7:48
 * @Version 1.0
 **/
@SpringBootApplication
public class App {
    /**
     * 20230905:111734
     * SpringBoot3.0之后不再使用SpringFactoriesLoader，而是Spring重新从META-INF/spring/目录下的org.springframework.boot.autoconfigure.AutoConfiguration.imports文件中读取了
     * https://juejin.cn/post/7210601680554246202
     */
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
