package groovytest.groovydsl

class MOPHandler {
    String name
    def invokeMethod(String method,Object params) {
        Object[] param = (Object[]) params;
        if(param.size() != 1){
            println "参数数量不对"
            return
        }
        params = param[0];
        println "MOPHandler was asked to invoke ${method}"
        if(params != null){
            params.each{ println "\twith parameter ${it}" }
        }
        MetaProperty property = this.metaClass.getMetaProperty(method)
        if(method != null){
            property.setProperty(this, params)
        }
        this.metaClass.invokeMethod(method, params);
    }

    def testMethod(String content){
        println "测试方法 参数:${content}"
    }

    static void main(String... args){
        MOPHandler handler = new MOPHandler();
        handler.metaClass.setProperty(handler, "name");
        handler.name("测试")
        println handler.getName()
    }

//    def getProperty(String property){
//        println "MOPHandler was asked for property ${property}"
//    }

//    static void main(String... args){
//        def hndler = new MOPHandler()
//        hndler.helloWorld()
//        hndler.createuser("Joe",18,new Date())
//        hndler.setName("zhangsan")
//        println hndler.getName() + " - " + hndler.name
//    }
    /*
     控制台输出
     MOPHandler was asked to invoke helloWorld
     MOPHandler was asked to invoke createuser
     with parameter Joe
     with parameter 18
     with parameter Wed May 25 15:21:49 GMT+08:00 2022
     MOPHandler was asked for property name
     null
     */
}

