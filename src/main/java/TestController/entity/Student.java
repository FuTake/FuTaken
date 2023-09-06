package TestController.entity;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@NoArgsConstructor
public class Student {
    @NotNull()
    String username;
    @NotNull
    int age;

    byte[] result;

    // 20230905:111120 不写 在编译时会报找不到符号的错误
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getResult() {
        return result;
    }

    public void setResult(byte[] result) {
        this.result = result;
    }
}
