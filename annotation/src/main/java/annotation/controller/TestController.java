package annotation.controller;

import annotation.entity.Student;
import annotation.entity.Student2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * nothing
 *
 * @ClassName TestController
 * @Description
 * @Author zsks
 * @Date 2021/11/23 20:32
 * @Version 1.0
 **/
@RestController
public class TestController {
    private Student2 student;

    public TestController(Student2 student){
        this.student = student;
    }

    @PostConstruct
    public void init(){
        System.out.println(student);
    }
}

