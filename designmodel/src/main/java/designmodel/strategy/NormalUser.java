package designmodel.strategy;

/**
 * 普通用户
 *
 * @ClassName NormalUser
 * @Description
 * @Author zsks
 * @Date 2021/12/12 10:16
 * @Version 1.0
 **/
public class NormalUser implements Strategy {
    @Override
    public void buy() {
        System.out.println("普通用户购买策略");
    }
}
