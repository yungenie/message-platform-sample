package com.exflyer.messageplatformsample.rabbitMQ;

import com.exflyer.messageplatformsample.common.utils.GsonUtil;
import com.exflyer.messageplatformsample.rabbitMQ.config.RabbitMQProducerConfig;
import com.exflyer.messageplatformsample.rabbitMQ.producer.RabbitMQProducerMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rabbitMQ/producer")
@Slf4j
public class RabbitMQController {

    private final RabbitMQProducerConfig rabbitMQProducerConfig;
    private final RabbitTemplate rabbitTemplate;
    private final GsonUtil gsonUtil;

    @PostMapping("/sample/queue")
    public void samplePublish(@RequestBody RabbitMQProducerMessage message) {

        log.info("producer message : {}", gsonUtil.getPrettyGsonStr(message));

        rabbitTemplate.convertAndSend(
            rabbitMQProducerConfig.EXCHANGE_NAME,
            rabbitMQProducerConfig.ROUTING_KEY,
            message
        );
        log.info("producer sending!");
    }


}
