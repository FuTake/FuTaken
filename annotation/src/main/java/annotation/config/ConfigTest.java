package annotation.config;

import annotation.entity.Student;
import annotation.entity.Student2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * nothing
 *
 * @ClassName ConfigTest
 * @Description
 * @Author zsks
 * @Date 2021/11/23 20:34
 * @Version 1.0
 **/
@Configuration
public class ConfigTest {

    @Bean
    @ConfigurationProperties(prefix = "spring")
    public Student get(){
        return new Student();
    }

    @Bean
    public Student2 getStudent(Student student){
        System.out.println("getStudent:" + student);
        Student2 s = new Student2();
        s.setAge(18);
        s.setUsername(student.getUsername());
        return s;
    }
}
