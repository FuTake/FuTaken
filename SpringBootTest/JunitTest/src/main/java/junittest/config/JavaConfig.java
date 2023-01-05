package junittest.config;

import junittest.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * nothing
 *
 * @ClassName JavaConfig
 * @Description
 * @Author zsks
 * @Date 2021/12/28 7:15
 * @Version 1.0
 **/
@Configuration
public class JavaConfig {

    @Bean
    public Student getBean(){
        Student student = new Student();
        student.setAge(20);
        student.setUsername("zhangsan");
        return student;
    }
}
