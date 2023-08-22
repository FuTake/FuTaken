package TestController.spitest;

import java.util.ServiceLoader;

/**
 * @author zhg
 * @date 2023/8/22
 */
public class Test {
    public static void main(String[] args) {
        // java spi机制 https://juejin.cn/post/7172176477189275678?searchId=20230822102006DAA81F986D72F033B137
        ServiceLoader<SpiTest> spiTests = ServiceLoader.load(SpiTest.class);
        for (SpiTest spiTest : spiTests) {
            System.out.println(spiTest.getClass());
            spiTest.run();
            spiTest.walk();
        }
    }
}
