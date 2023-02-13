package groovytest.groovydsl

import groovy.transform.BaseScript
import groovytest.entity.RuleContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scripting.ScriptCompilationException


abstract class GroovyBaseScript_rule extends Script {
    def rule(Closure closure){
        def metadata = new Metadata()
        closure.delegate = metadata
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure()
    }
}

class Metadata extends BaseRule{
//    String name
//    int age
//    String gender
//    RuleContext context
    def metadata(Closure closure){
        closure.delegate = this
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
        println "${this.name} - ${age} - ${gender}"
    }

    def logic(Closure closure){
        closure.delegate = this
        closure.resolveStrategy = Closure.DELEGATE_FIRST
        closure()
    }

}

abstract class BaseRule{
    def methodMissing(String name, args) {
        println "methodMissing key=${name} args=${args}"
        MetaProperty p = this.metaClass.getMetaProperty(name)
        if (p != null) {
            def arg = args
            if (args instanceof Object[] && ((Object[]) args).length == 1) {
                arg = ((Object[]) args)[0]
            }
            // 这里添加新属性，this里没有的属性会执行propertyMissing方法
//            this.metaClass.setProperty(this, name, arg)
            if("context".equals(name)){
                p.setProperty(this, new RuleContext(name, age, gender))
                return
            }
            p.setProperty(this, arg)
//        }
        }
    }
    def propertyMissing(String name, def args){
        println "propertyMissing key=${name} args=${args} isExist:${property}"
        this.metaClass.setProperty(this, name, args);
        // this.setProperty(name, args) 这里会因为this没有name这个属性，导致嵌套执行propertyMissing方法
    }
}



abstract class GroovyBaseScript extends Script{
    private static Logger logger = LoggerFactory.getLogger(GroovyBaseScript.class);
    def rule(String ruleId, Closure closure){
        def email = new EmailSpec2()
        closure.delegate = email
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure()
    }
    def rule(Closure closure){
        def email = new EmailSpec2()
        closure.delegate = email
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure()
        return "OK"
    }
    def left(String args){
        println "${args}"
        this
    }
    def right(String args){
        println "${args}"
        this
    }
}

class BodySpec2 {
    void useful(String content){
        println "我有什么用呢? ${content}"
    }
}

class EmailSpec2 {
    void from(String from) { println "From: $from"}
    void to(String... to) { println "To: $to"}
    void subject(String subject) { println "Subject: $subject"}
    void body(Closure body) {
        def bodySpec = new BodySpec()
        def code = body.rehydrate(bodySpec, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}