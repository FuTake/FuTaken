package TestController.entity;

import com.sun.istack.internal.NotNull;
import lombok.Data;

/**
 * nothing
 *
 * @ClassName Student
 * @Description
 * @Author zsks
 * @Date 2021/12/20 7:49
 * @Version 1.0
 **/
@Data
public class Student {
    @NotNull()
    String username;
    @NotNull
    int age;

    byte[] result;
}
