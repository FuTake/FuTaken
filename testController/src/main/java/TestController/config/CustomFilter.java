package TestController.config;

import TestController.entity.Student;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName CustomFilter
 * @Description
 * @Author zsks
 * @Date 2022/1/9 13:20
 * @Version 1.0
 **/
public class CustomFilter implements ValueFilter {
    @Override
    public Object process(Object o, String s, Object o1) {
        if(o1 instanceof byte[]){
            return new String((byte[])o1);
        }
        return o1;
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(5);
        student.setUsername("zhangsan");
        student.setResult("result".getBytes());
        //byte[] result = JSONObject.toJSONBytes(student); //{"age":5,"result":"cmVzdWx0","username":"zhangsan"}
        byte[] result = JSONObject.toJSONBytes(student, new CustomFilter()); //{"age":5,"result":"result","username":"zhangsan"}
        System.out.println(new String(result));
    }
}
