package com.exflyer.messageplatformsample.rabbitMQ.producer;

import lombok.Getter;

@Getter
public class RabbitMQProducerMessage {

    private String message1;
    private String message2;

    public void changeMessage2(String message2) {
        this.message2 = message2;
    }
}
