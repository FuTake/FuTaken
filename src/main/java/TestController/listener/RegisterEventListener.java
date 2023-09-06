package TestController.listener;

import TestController.entity.RegisterSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class RegisterEventListener {

    private static final Logger log = LoggerFactory.getLogger(RegisterEventListener.class);

    @EventListener
    public void handleNotifyEvent(RegisterSuccessEvent event) {
        log.info("监听到用户注册成功事件：" +
                "{}，你注册成功了哦。记得来玩儿~", event.getUserName());
    }

}
