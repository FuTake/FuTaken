package tomcattest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhg
 * @date 2023/8/16
 */
@RestController
@RequestMapping("/tomcat")
public class TestController {

    @RequestMapping("test")
    public String test(String param){
        LocalDateTime time = LocalDateTime.now();
        String timeStr = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return String.join("_", "ok", param, timeStr);
    }
}
