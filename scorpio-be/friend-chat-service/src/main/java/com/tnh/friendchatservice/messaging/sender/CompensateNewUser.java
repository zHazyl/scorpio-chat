package com.tnh.friendchatservice.messaging.sender;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Map;

@RequiredArgsConstructor
public class CompensateNewUser {

    private final RabbitTemplate template;
    private final FanoutExchange fanout;

    public void sendCompensate(String[] id) {
        var converter = template.getMessageConverter();
        var messageProperties = new MessageProperties();
        var message = converter.toMessage(id, messageProperties);
        template.send(fanout.getName(), "", message);
    }

}
