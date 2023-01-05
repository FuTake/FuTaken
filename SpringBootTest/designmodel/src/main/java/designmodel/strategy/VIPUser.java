package designmodel.strategy;

/**
 * 会员用户
 *
 * @ClassName VIPUser
 * @Description
 * @Author zsks
 * @Date 2021/12/12 10:17
 * @Version 1.0
 **/
public class VIPUser implements Strategy {
    @Override
    public void buy() {
        System.out.println("会员用户购买策略");
    }
}
