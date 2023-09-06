package TestController.springspitest;

import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.stereotype.Component;
import springspitest.SpiTest;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhg
 * @date 2023/8/22
 */
@Component
public class SpringSpiTest {

    @Resource(name = "dog")
    private SpiTest dogSpiTest;

    @Resource(name = "cat")
    private SpiTest catSpiTest;

    @PostConstruct
    public void init(){
        dogSpiTest.run();
        dogSpiTest.walk();
        catSpiTest.run();
        catSpiTest.walk();
    }

    public static void main(String[] args) {
        // resourcs/META-INF/spring.fatories中配置
        List<SpiTest> spiTests = SpringFactoriesLoader.loadFactories(SpiTest.class, SpringSpiTest.class.getClassLoader());
        for (SpiTest test : spiTests) {
            System.out.println("获取到LoadBalance对象:" + test);
            test.run();
            test.walk();
        }
    }

}
