package nacostest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhg
 * @date 2023/8/16
 */
@RestController
@RequestMapping("/tomcat")
@Slf4j
@RefreshScope
public class TestController {

    @Value("${nacosTest.param1:nothing}")
    private String param1;

    @PostConstruct
    public void init(){
      log.info("param1:{}", param1);
    }

    @RequestMapping("test")
    public String test(String param){
        LocalDateTime time = LocalDateTime.now();
        String timeStr = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.join("_", param1, param, timeStr);
    }
}
