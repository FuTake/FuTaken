package cc.bitky.demo.bitkylinkafka;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@SpringBootApplication
public class BitkylinKafkaApplication {
    private static final String topic = "test-bitkylin";
    public static void main(String[] args) {
        SpringApplication.run(BitkylinKafkaApplication.class, args);
    }

    //./kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic test-bitkylin 查看topic的分区情况

    @Bean
    public NewTopic batchTopic() {
        // topicName,partionNum, 新主题的复制因子
        return new NewTopic(topic, 3, (short) 1);
    }

    // groupId相同的会分配到同一个partition，同一个partition的消费者就是集群模式
    // 不同groupId的消费者都会收到消息，这个就是广播模式
    //
//    @KafkaListener(groupId = "webGroup1", topics = topic, concurrency = "2")
//    public void listen1(String input) {
//        log.info("listen1: {}", input);
//    }

//    @KafkaListener(groupId = "webGroup3", topics = topic, concurrency = "2")
//    public void listen2(String input) {
//        log.info("listen2: {}", input);
//    }
    // groupId相同的会分配到同一个partition，同一个partition的消费者就是集群模式
    // 不同groupId的消费者都会收到消息，这个就是广播模式
//    @KafkaListener(groupId = "webGroup3", topics = topic)
//    public void listen3(String input) {
//        try {
//            log.info("listen3: {} 线程:{}", input, Thread.currentThread().hashCode());
//            Thread.sleep(10 * 1000);
//        }catch (Exception e){
//            log.error("listen3", e);
//        }
//    }

    /*
        配置topic和分区,可以配置多个
        topic为队列名称
        partitions表示值的的分区，这里指定了0和2分区
        partitionOffsets表示详细的指定分区，partition表示那个分区，initialOffset表示Offset的初始位置
        https://blog.csdn.net/weixin_45059961/article/details/125236337 指定partition会破坏kafka的rebalance导致相同groupId的消费者都会消费消息
     */
    @KafkaListener(groupId = "webGroup3", topicPartitions = { @TopicPartition(topic = topic,
            partitions = { "0", "2" }
            /*,partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "4")*/)
    })
    public void listen4(String input){
        log.info("listen4: {}", input);
    }

    @KafkaListener(groupId = "webGroup3", topicPartitions = { @TopicPartition(topic = topic,
            partitions = { "1" })
    })
    public void listen5(String input){
        log.info("listen5: {}", input);
    }
}
