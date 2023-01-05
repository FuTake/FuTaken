package groovytest.groovydsl

import sun.applet.Main

//定义二级闭包格式
class Person5{
    String name
    int age

    void eat(String food){
        println("年龄${age}岁的${name}做的${food}真难吃")
    }
    void name(name){
        this.name = name
    }

    void age(age){
        this.age = age
    }


    @Override
    public String toString() {
        return "Person5{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}



class Main{
    //dsl脚本
    def static user = {
        name "tom" //调用的都是方法
        age 12  //同上
        eat("苦瓜") //同上
        name "lisi"
        age 15
        eat("大苦瓜")
    }
    static void main(String... args){
        Main main = new Main()
        Person5 person = new Person5(name:"zhangsan", age:15)
        user.delegate = person
        user.call()
    }
}
