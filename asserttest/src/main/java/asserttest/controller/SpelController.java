package asserttest.controller;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * nothing
 *
 * @ClassName SpelController
 * @Description
 * @Author zsks
 * @Date 2021/11/8 21:50
 * @Version 1.0
 **/
@RestController
@RequestMapping("/spel")
public class SpelController {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(parse("${username}", student, String.class));
        System.out.println(parse("${hobbies[0]}", student, String.class));
        System.out.println(parse("${other['gender']}", student, String.class));
    }

    public static <T> T parse(String key, Object obj, Class<T> clazz){
        ExpressionParser parser = new SpelExpressionParser();
        //放入内容
        EvaluationContext context = new StandardEvaluationContext(obj);
        //表达式格式${...}
        TemplateParserContext template = new TemplateParserContext("${", "}");
        //根据表达式获得值并转为指定类型
        return parser.parseExpression(key, template).getValue(context, clazz);
    }
}
class Student{
    //必须用public修饰，否则读不到
    public String username;
    public int age;
    public List<String> hobbies;
    public Map<String, String> other;
    public Student(){
        username = "zhangsan";
        age = 16;
        hobbies = Arrays.asList("sport", "game", "study");
        other = new HashMap<>();
        other.put("gender", "man");
        other.put("phone", "123123123");

    }
}
