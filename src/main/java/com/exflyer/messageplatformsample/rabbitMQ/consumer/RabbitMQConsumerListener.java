package com.exflyer.messageplatformsample.rabbitMQ.consumer;

import com.exflyer.messageplatformsample.common.utils.GsonUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumerListener {

    private final GsonUtil gsonUtil;

    /**
     * 단일 문자열 처리
     */
/*    @RabbitListener(queues = "sample.queue")
    public void receiveMessage(String message) {
        RabbitMQConsumerMessage consumerMessage = gsonUtil.getGson()
            .fromJson(message, RabbitMQConsumerMessage.class);
        log.info("consumer data : \n{}", gsonUtil.getPrettyGsonStr(consumerMessage));
    }*/

    /**
     * 발행한 Object로 처리
     */
/*    @RabbitListener(queues = "sample.queue", containerFactory = "batchContainerFactory")
    public void receiveBatchMessage(List<RabbitMQProducerMessage> messages) {

        for (RabbitMQProducerMessage s: messages) {
            log.info("consumer data : \n{}", gsonUtil.getPrettyGsonStr(s));
        }
    }*/

    /**
     * List 문자열로 받아 매핑할 Object로 바인딩
     */
    @RabbitListener(queues = "sample.queue", containerFactory = "batchContainerFactory")
    public void receiveBatchMessage(List<String> messages) {
        for (String message: messages) {
            RabbitMQConsumerMessage consumerMessage = gsonUtil.getGson()
                .fromJson(message, RabbitMQConsumerMessage.class);
            log.info("consumer data : \n{}", gsonUtil.getPrettyGsonStr(consumerMessage));
        }
    }

}
