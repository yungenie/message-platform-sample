package com.exflyer.messageplatformsample.rabbitMQ.consumer;

import com.exflyer.messageplatformsample.common.utils.GsonUtil;
import com.exflyer.messageplatformsample.rabbitMQ.config.RabbitMQSampleConfig;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
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
/*    @RabbitListener(queues = {RabbitMQSampleConfig.QUEUE_NAME})
    public void receiveMessage(String message) {
        RabbitMQConsumerMessage consumerMessage = gsonUtil.getGson()
            .fromJson(message, RabbitMQConsumerMessage.class);
        log.info("consumer data : \n{}", gsonUtil.getPrettyGsonStr(consumerMessage));
    }*/

    /**
     * 발행한 Object로 처리
     */
/*    @RabbitListener(queues = {RabbitMQSampleConfig.QUEUE_NAME}, containerFactory = "batchContainerFactory")
    public void receiveBatchMessage(List<RabbitMQProducerMessage> messages) {

        for (RabbitMQProducerMessage s: messages) {
            log.info("consumer data : \n{}", gsonUtil.getPrettyGsonStr(s));
        }
    }*/

    /**
     * List 문자열로 받아 매핑할 Object로 바인딩
     */
/*    @RabbitListener(queues = {RabbitMQSampleConfig.QUEUE_NAME}, containerFactory = "batchContainerFactory")
    public void receiveBatchMessage(List<String> messages) {
        for (String message: messages) {
            RabbitMQConsumerMessage consumerMessage = gsonUtil.getGson()
                .fromJson(message, RabbitMQConsumerMessage.class);
            log.info("consumer data : \n{}", gsonUtil.getPrettyGsonStr(consumerMessage));
        }
    }*/


    /**
     * 수동 ACK 모드 테스트 (에러발생 시 기존 큐에 다시 넣기)
     * application.yml에서 spring.rabbitmq.listener.simple.acknowledge-mode: manual 활성화
     * requeue : true
     */
    @RabbitListener(queues = {RabbitMQSampleConfig.QUEUE_NAME})
    public void listenerManualAck(Message message, Channel channel) throws Exception {

        try {
            // 메시지 처리 로직
            String body = new String(message.getBody());

            if (!body.isEmpty()) {
                throw new RuntimeException("Forced error for testing");
            }

            log.info("[listenerManualAck 모니터링하고 있는 message] - [{}]", gsonUtil.getPrettyGsonStr(body));

            // 메시지 수동 확인
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 메시지 거부 및 다시 큐에 넣기
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
