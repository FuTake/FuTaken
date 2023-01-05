package TestController.config;

/**
 * @ClassName InternalStaticClass
 * @Description
 * @Author zsks
 * @Date 2022/1/9 14:30
 * @Version 1.0
 **/
public class InternalStaticClass {

    private InternalStaticClass(){
        int i = 1/0;
    }

    static{
        System.out.println("static InternalStaticClass");
    }

    private static class InternalClass{
        private static final InternalStaticClass internalclass = new InternalStaticClass();
        static{
            System.out.println("static InternalClass");
        }
    }

    public static InternalStaticClass getInternalStaticClass(){
        return InternalClass.internalclass;
    }

    public static void main(String[] args) {
        try {
            InternalStaticClass.getInternalStaticClass();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

}
