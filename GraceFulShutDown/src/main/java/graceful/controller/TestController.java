package graceful.controller;

import graceful.config.OnOff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * nothing
 *
 * @ClassName TestController
 * @Description
 * @Author zsks
 * @Date 2021/12/23 7:32
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private OnOff onOff;

    @RequestMapping("/test")
    public String test() throws InterruptedException {
        while(true){
            System.out.println(onOff.isOnoff());
            Thread.sleep(1000);
            if(onOff.isOnoff()){
                break;
            }
        }
        return "ok";
    }
}
