package TestController.connectionnum.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 本来想测试SpringBoot链接数，也没测
 */
@RequestMapping("/connection")
@RestController
@Slf4j
public class ConnectionController {

    @RequestMapping("/receive")
    public String receive(){
        LocalDateTime time = LocalDateTime.now();
        String timeStr = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return timeStr;
    }
}