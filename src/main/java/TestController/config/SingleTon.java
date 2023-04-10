package TestController.config;

/**
 * @ClassName SingleTon
 * @Description
 * @Author zsks
 * @Date 2022/1/9 14:44
 * @Version 1.0
 **/
public class SingleTon {
    private SingleTon() {
    }

    static{
        System.out.println("项目启动时，加载");
    }

    private static class Holder {
        private static final SingleTon INSTANCE = new SingleTon();
        static{
            System.out.println("调用getInstance时加载");
        }
    }

    public static SingleTon getInstance() {
        return Holder.INSTANCE;
    }

    public static void main(String[] args) {
        String action = null;
        switch(action){
            case "zhangsan":
                //逻辑
                break;
            case "wangwu":
                //逻辑
                break;
            default:
                //逻辑
        }
    }
}
