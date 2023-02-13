package junittest.test;

import com.alibaba.fastjson.JSONObject;
import junittest.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * nothing
 *
 * @ClassName TestApp
 * @Description
 * @Author zsks
 * @Date 2021/12/28 7:14
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestApp {

    @Autowired
    private Student student;

    @Test
    public void test(){
        System.out.println(JSONObject.toJSONString(student));
    }
}
