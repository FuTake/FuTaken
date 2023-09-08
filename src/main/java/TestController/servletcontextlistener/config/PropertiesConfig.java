package TestController.servletcontextlistener.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(PropertiesConfig.class);

//    @PostConstruct
    public void init(){
        LocalDateTime time =  LocalDateTime.now();
        log.info(time.format(DateTimeFormatter.ofPattern("yyyy-MM-ss HH:mm:ss")) + "@PostConstruct");
    }

}
