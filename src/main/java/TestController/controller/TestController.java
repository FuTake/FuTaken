package TestController.controller;

import TestController.entity.RegisterSuccessEvent;
import TestController.entity.Student;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
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
    public String test2(HttpServletRequest request) throws InterruptedException {
        System.out.println(request.getQueryString());
        Thread.sleep(20000);
        System.out.println(request.getQueryString());
        return "ok";
    }
    @RequestMapping("/eventlistener")
    public String publishEvnet(){
        applicationContext.publishEvent(new RegisterSuccessEvent("eventListener test"));
        return "ok";
    }

    @RequestMapping("/future")
    public String future() throws Exception {
        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                try{
                    int i = 1/0;
                }catch (Exception e){
                    return e;
                }
                return null;
            }
        });
        // 执行其他逻辑
        // 接口逻辑执行完的时候去判断task的运行状态，这里会阻塞
        Object result = task.get();
        if(result instanceof Exception){
            throw  (Exception)result;
        }
        return "success";
    }

    @RequestMapping("/getRealPath")
    public String getRealPath(HttpServletRequest req){
        String result = req.getRealPath("/zhangsan");
        System.out.println(result);
        return result;
    }
}
