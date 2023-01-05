package okhttpTest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * nothing
 *
 * @ClassName Student
 * @Description
 * @Author zsks
 * @Date 2021/11/21 11:11
 * @Version 1.0
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    public String username;
    public int age;
    public String gender;
    public List<Student> childs;
    public Map<String, String> attributes;
}
