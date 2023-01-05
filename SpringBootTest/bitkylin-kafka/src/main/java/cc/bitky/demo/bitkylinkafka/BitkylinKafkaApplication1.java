package cc.bitky.demo.bitkylinkafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@SpringBootApplication
public class BitkylinKafkaApplication1{

    public static void main(String[] args) {
        SpringApplication.run(BitkylinKafkaApplication1.class, args);
    }

    @Autowired
    private KafkaTemplate<Object, Object> template;


    // groupId相同的会分配到同一个partition，同一个partition的消费者就是集群模式
    // 不同groupId的消费者都会收到消息，这个就是广播模式
    @KafkaListener(groupId = "webGroup3", topics = "test-bitkylin")
    public void listen(String input) {
        log.info("消息接收: {}", input);
    }
}
