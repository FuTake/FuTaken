package TestController.entity;

import lombok.Data;

/**
 * @EventListener功能测试
 */
@Data
public class RegisterSuccessEvent {

    private String userName;

    public RegisterSuccessEvent(String userName) {
        this.userName = userName;
    }
}
