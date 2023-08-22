package TestController.servletcontextlistener.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhg
 * @date 2023/8/21
 */
@Component
public class PropertiesConfig {
    @Value("${servletContext.param1}")
    private String value;


    @PostConstruct
    public void init(){
        LocalDateTime time =  LocalDateTime.now();
        System.out.println(time.format(DateTimeFormatter.ofPattern("yyyy-MM-ss HH:mm:ss")) + "@PostConstruct");
    }

}
