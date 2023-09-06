package TestController.spitest.impl;

import TestController.spitest.SpiTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhg
 * @date 2023/8/22
 */
public class DogImpl implements SpiTest {

    private static final Logger log = LoggerFactory.getLogger(DogImpl.class);

    @Override
    public void run() {
        log.info("Dog is running");
    }

    @Override
    public void walk() {
        log.info("Dog is walking");
    }
}
