package rocketmqtest.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author zhg
 * @date 2023/8/24
 *
 *
 */

@RocketMQMessageListener(topic = "rocketmq-test", consumeThreadMax = 1,consumerGroup = "rocketmq-consumer-1",consumeMode = ConsumeMode.CONCURRENTLY, messageModel = MessageModel.CLUSTERING)
@Component
public class MsgListenerTwo implements RocketMQListener<MessageExt> {

    private static final Logger log = LoggerFactory.getLogger(MsgListenerTwo.class);

    @Override
    public void onMessage(MessageExt messageExt) {
        String content = new String(messageExt.getBody(), StandardCharsets.UTF_8);
        log.info("msgListener2 content = " + messageExt.getMsgId() + " - " + messageExt.getQueueId() + " - " + content);
    }
}