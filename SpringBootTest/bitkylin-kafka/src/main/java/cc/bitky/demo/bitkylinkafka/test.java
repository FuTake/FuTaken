package cc.bitky.demo.bitkylinkafka;

import java.util.Random;

public class test {
    public static void main(String[] args) throws InterruptedException {
        while(true){
            System.out.println(new Random().nextInt(3));
            Thread.sleep(100);
        }
    }
}
