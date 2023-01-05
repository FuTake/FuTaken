package cc.bitky.demo.bitkylinkafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@SpringBootApplication
public class BitkylinKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitkylinKafkaApplication.class, args);
    }


    @KafkaListener(groupId = "webGroup3", topics = "test-bitkylin")
    public void listen(String input) {
        log.info("消息接收: {}", input);
    }
}
