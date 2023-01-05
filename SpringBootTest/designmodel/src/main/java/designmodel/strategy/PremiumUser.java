package designmodel.strategy;

/**
 * 高级用户
 *
 * @ClassName PremiumUser
 * @Description
 * @Author zsks
 * @Date 2021/12/12 10:17
 * @Version 1.0
 **/
public class PremiumUser implements Strategy {

    @Override
    public void buy() {
        System.out.println("高级用户购买策略");
    }
}
