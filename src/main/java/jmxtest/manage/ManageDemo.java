package jmxtest.manage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jmx.export.annotation.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhg
 * @date 2023/9/4
 * 资料：https://blog.csdn.net/u014155085/article/details/120183461
 */
@Service
@ManagedResource(description = "spring jmx注解的demo类") //Spring指示向 JMX 服务器注册类的实例
@Slf4j
public class ManageDemo {

    @ManagedOperation(description = "执行方法示例 execReturnString") //Spring指示将指定方法公开为 JMX 操作（仅get/set方法无效）
    //@ManagedOperationParameter 仅入参的文档注释，没实际意义
    @ManagedOperationParameters(value = {
            @ManagedOperationParameter(name = "paramOne", description = "this is paramOne desc"),
            @ManagedOperationParameter(name = "paramTwo", description = "this is paramTwo desc")
    })
    public String execReturnString(String paramOne, String paramTwo) {
        return paramOne + "-" + paramTwo;
    }

    @ManagedOperation(description = "执行方法示例 execVoid")
    public void execVoid() {
        log.info("execVoid已执行");
    }

    @ManagedAttribute(description = "ManagedAttribute 使用示例")  //Spring将指定的 bean 属性公开为 JMX 属性（仅get/set方法生效）
    public String getProperties() {
        log.info("this is ManagedAttribute" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return "this is ManagedAttribute" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}