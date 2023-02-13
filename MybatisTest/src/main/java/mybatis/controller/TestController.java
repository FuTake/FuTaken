package mybatis.controller;

import com.alibaba.fastjson.JSONObject;
import mybatis.entity.Student;
import mybatis.mapper.db1.TestMapper;
import mybatis.mapper.db2.TestMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller
 *
 * @ClassName TestController
 * @Description
 * @Author zsks
 * @Date 2021/12/16 22:09
 * @Version 1.0
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestMapper testMapper ;
    @Autowired
    TestMapper2 testMapper2;

    @RequestMapping("/select")
    public void select(){
        List<Student> students1 = testMapper2.getAllStudent();
        System.out.println(JSONObject.toJSONString(students1));
        List<Student> students = testMapper.getAllStudent();
        System.out.println(JSONObject.toJSONString(students));
    }

    @Autowired
    DataSourceTransactionManager manager;
    @Autowired
    TransactionDefinition definition;
    public void insert(){
        TransactionStatus status = manager.getTransaction(definition);
        try{
            testMapper.insert();
            manager.commit(status);
        }catch(Exception e){
            manager.rollback(status);
        }
    }
}
