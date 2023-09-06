package TestController.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @EventListener功能测试
 * @Data在引入spring-boot-starter-autoconfigure后无效了
 */
@Data
public class RegisterSuccessEvent {

    private String userName;

    public RegisterSuccessEvent(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
