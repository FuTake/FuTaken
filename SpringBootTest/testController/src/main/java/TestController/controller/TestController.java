package TestController.controller;

import TestController.entity.Student;
import com.alibaba.fastjson.JSONObject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @RequestMapping("/test")
    public void test(@Validated @RequestBody Student student){
        System.out.println(JSONObject.toJSONString(student));
    }
    @RequestMapping("/test2")
    public String test2(){
        return "ok";
    }
}
