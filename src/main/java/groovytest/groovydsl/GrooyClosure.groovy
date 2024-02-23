package groovytest.groovydsl

class BodySpec {
    void useful(String content){
        println "我有什么用呢? ${content}"
    }
}

class EmailSpec {
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

test {
    name "zhangsan"
}

class Main2 {
    def cc = {
        from "beijing"
        to "shanghai"
        subject "我"
        body {
            useful "不知道"
        }
    }
    static void main(String... args) {
        Main2 main2 = new Main2();
        if(main2.cc in Closure){
            main2.execute(main2.cc)
        }
    }
    def execute(Closure cl){
        def email = new EmailSpec()
        cl.delegate = email
        cl()
    }
    def email(Closure cl) {
        def email = new EmailSpec()
        def code = cl.rehydrate(email, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code()
    }
}

