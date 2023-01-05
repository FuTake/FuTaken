package cc.bitky.demo.bitkylinkafka;


import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Properties;

public class BitkylinKafkaApplication2{
    private static Logger log = LoggerFactory.getLogger("sendMessage");

    private static org.apache.kafka.clients.producer.KafkaProducer producer;
    private final static String TOPIC = "test-bitkylin";
    public BitkylinKafkaApplication2(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9081,localhost:9082,localhost:9083");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        //设置分区类,根据key进行数据分区
        producer = new org.apache.kafka.clients.producer.KafkaProducer(props);
    }

    public static void main(String[] args) {
        new BitkylinKafkaApplication2().test();
    }

    public void test(){
        log.info("消息发送：");
        try {

            while(true) {
                String message = "message-" + LocalDateTime.now();
                log.info("发送消息：{}", message);
                producer.send(new ProducerRecord<String, String>(TOPIC, message));
                Thread.sleep(500);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            producer.close();
        }
    }
}
