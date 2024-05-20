package com.exflyer.messageplatformsample.rabbitMQ.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;

public class ListStringMessageConverter extends AbstractMessageConverter {
    @Override
    public Object fromMessage(Message message) throws MessageConversionException {
        byte[] body = message.getBody();
        if (body == null) {
            return null;
        }
        return new String(body);
    }

    @Override
    protected Message createMessage(Object object, MessageProperties messageProperties) {
        return null;
    }
}




