package TestController.springspitest;

import org.springframework.stereotype.Component;
import springspitest.SpiTest;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

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
}
