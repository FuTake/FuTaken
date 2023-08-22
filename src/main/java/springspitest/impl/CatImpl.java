package springspitest.impl;

import org.springframework.stereotype.Component;
import springspitest.SpiTest;

/**
 * @author zhg
 * @date 2023/8/22
 */
@Component("cat")
public class CatImpl implements SpiTest {
    @Override
    public void run() {
        System.out.println("Cat is running");
    }

    @Override
    public void walk() {
        System.out.println("Cat is walking");
    }
}
