package TestController.spitest.impl;

import TestController.spitest.SpiTest;

/**
 * @author zhg
 * @date 2023/8/22
 */
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
