package annotation.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * nothing
 *
 * @ClassName Student
 * @Description
 * @Author zsks
 * @Date 2021/11/23 20:33
 * @Version 1.0
 **/
@Data
public class Student {
    String username;
    int age;
}
