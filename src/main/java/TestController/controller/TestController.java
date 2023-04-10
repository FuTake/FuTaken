package TestController.controller;

import TestController.entity.RegisterSuccessEvent;
import TestController.entity.Student;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * nothing
 *
 * @ClassName TestController
 * @Description
 * @Author zsks
 * @Date 2021/12/20 7:49
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("/test")
    public void test(@Validated @RequestBody Student student){
        System.out.println(JSONObject.toJSONString(student));
    }
    @RequestMapping("/test2")
    public String test2(HttpServletRequest request){
        System.out.println(request.getQueryString());
        return "ok";
    }
    @RequestMapping("/eventlistener")
    public String publishEvnet(){
        applicationContext.publishEvent(new RegisterSuccessEvent("eventListener test"));
        return "ok";
    }
}
