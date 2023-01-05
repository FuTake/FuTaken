package groovytest.groovydsl
import groovy.transform.BaseScript

@BaseScript GroovyBaseScript_rule _


rule {
    /*
        先执行 methodMissing方法，methodMissing方法发现这是个属性赋值操作，然后调用propertyMissing
     */
    metadata {
        name "zhangsan"
        age 15
        gender "man"
    }

    logic {
        gender = context?.student?.priority?.gender
        println "logic closure gender = ${gender}"
        valid = gender != null && gender == ~ "\\w[Male, Female]"
        println "logic.valid=$valid"
    }
}