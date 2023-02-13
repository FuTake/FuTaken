package groovytest.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class RuleContext {
    Student student;

    public RuleContext(String name, int age, String gender){
        student = new Student();
        student.setPriority(new Priority(name, age, gender));
    }
}

@Data
class Student{
    Priority priority;
}
@Data
@AllArgsConstructor
class Priority{
    String name;
    int age;
    String gender;
}
