package com.exflyer.messageplatformsample.rabbitMQ.consumer;

import com.exflyer.messageplatformsample.common.utils.GsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConsumerListener {

    private final GsonUtil gsonUtil;

    @RabbitListener(queues = "sample.queue")
    public void receiveMessage(String message) {
        RabbitMQConsumerMessage consumerMessage = gsonUtil.getGson()
            .fromJson(message, RabbitMQConsumerMessage.class);
        log.info("consumer data : {}", gsonUtil.getPrettyGsonStr(consumerMessage));
    }

}
