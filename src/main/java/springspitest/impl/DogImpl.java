package springspitest.impl;
import org.springframework.stereotype.Component;
import springspitest.SpiTest;

/**
 * @author zhg
 * @date 2023/8/22
 */
@Component("dog")
public class DogImpl implements SpiTest {
    @Override
    public void run() {
        System.out.println("Dog is running");
    }

    @Override
    public void walk() {
        System.out.println("Dog is walking");
    }
}
