package okhttpTest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import groovy.lang.Binding;
import groovy.lang.GroovyClassLoader;
import groovy.lang.Script;
import okhttpTest.entity.Student;
import org.junit.Test;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.*;
import java.util.*;

/**
 * nothing
 *
 * @ClassName TestController
 * @Description
 * @Author zsks
 * @Date 2021/11/9 22:26
 * @Version 1.0
 **/
@RestController
@RequestMapping("groovy")
public class TestController {
    static Student student = new Student();
    static {
        System.out.println("ceshi");
    }
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
        InputStream inputStream = TestController.class.getClassLoader().getResourceAsStream("script/test.groovy");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String content = null;
        while((content = bufferedReader.readLine()) != null){
            sb.append(content);
        }
        GroovyClassLoader loader = new GroovyClassLoader();
        Class clazz = loader.parseClass(sb.toString());
        Script script = (Script) clazz.newInstance();
        Binding binding = new Binding();
        Map<String, Object> map = new HashMap<>();
        map.put("requestId", UUID.randomUUID().toString());
        map.put("double", 1.123123123123);
        binding.setProperty("input", map);
        script.setBinding(binding);
        String result = (String) script.run();
        System.out.println(result);

    }

    @Test
    public void test() throws InterruptedException {
        System.out.println(String.format("%s - %s", Thread.currentThread().getName(), student));
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            student.setUsername("lisi");
            System.out.println(String.format("%s - %s", Thread.currentThread().getName(), student));
        }).start();
        new Thread(()->{
            student.setUsername("wangwu");
            System.out.println(String.format("%s - %s", Thread.currentThread().getName(), student));
        }).start();
        Thread.sleep(2000);
        System.out.println(String.format("%s - %s", Thread.currentThread().getName(), student));
    }

    @Test
    public void test2(){
        String s = "abc";
        String s3 = "a";
        String s4 = "bc";
        String s5 = "a" + "bc";
        System.out.println(s == (s3+s4)); //false
        System.out.println(s == s5); //true
    }

    ThreadLocal threadLocal = new ThreadLocal();

    @Test
    public void test3() throws InterruptedException {
        new Thread(()->{
            threadLocal.set("zhangsan");
            ThreadLocal threadLocal1 = new ThreadLocal();
            System.out.println(threadLocal1.get());
            System.out.println(threadLocal.get());
        }).start();
        Thread.sleep(500);
        System.out.println(threadLocal.get());
    }
    @Test
    public void test4(){
        @Valid Student student2 = new Student();
        System.out.println(student2);
    }
    @Test
    public void test5() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        Student student = Student.builder()
                .username("zhangsan")
                .age(15)
                .gender("女")
                .build();
        Map<String, String> attributes = new HashMap<>();
        attributes.put("attr1", "1");
        attributes.put("attr2", "2");
        attributes.put("attr3", "3");
        student.setAttributes(attributes);
        List<Student> studentList = new ArrayList<>();
        for(int i=0; i<2; i++){
            Student tempStudent = Student.builder()
                    .age(i)
                    .username("zhang"+i)
                    .gender("女").build();
            studentList.add(tempStudent);
        }
        student.setChilds(studentList);
        node.putPOJO("student", student);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        System.out.println(mapper.writeValueAsString(node));

    }
    @Test
    public void test6() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String content = "{\n" +
                "    \"username\":\"zhangsan\",\n" +
                "    \"age\":15,\n" +
                "    \"gender\":\"man\"\n" +
                "}";
        Student student = mapper.readValue(content, Student.class);
        System.out.println(student);
    }

    @Cacheable
    public void test7(){

    }

}
