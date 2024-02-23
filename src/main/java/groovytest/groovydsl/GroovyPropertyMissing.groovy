package groovytest.groovydsl


class GroovyPropertyMissing{
    def propertyMissing(String name, def args){
        println "propertyMissing key=${name} value=${args}"
        this.metaClass.setProperty(this, name, args);
    }
    def getProperty(String name){
        println this.getProperty(name)
    }

    static void main(String... args){
        def clazz = new GroovyPropertyMissing();
        clazz.name = "zhangsan"
        clazz.getProperty("name")
    }

}

