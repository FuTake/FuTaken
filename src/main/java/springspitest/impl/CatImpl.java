package springspitest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import springspitest.SpiTest;

/**
 * @author zhg
 * @date 2023/8/22
 */
@Component("cat")
public class CatImpl implements SpiTest {

    private static final Logger log = LoggerFactory.getLogger(CatImpl.class);

    @Override
    public void run() {
        log.info("Cat is running");
    }

    @Override
    public void walk() {
        log.info("Cat is walking");
    }
}
