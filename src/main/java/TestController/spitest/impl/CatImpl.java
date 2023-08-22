package TestController.spitest.impl;

import TestController.spitest.SpiTest;

/**
 * @author zhg
 * @date 2023/8/22
 */
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
