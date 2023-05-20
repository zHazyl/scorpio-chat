package com.tnh.authservice.messaging.sender;

import com.tnh.authservice.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RequiredArgsConstructor
public class VotingSender {
    private final RabbitTemplate template;
    private final FanoutExchange fanout;
    public void vote(UserDTO userDTO) {
        var converter = template.getMessageConverter();
        var messageProperties = new MessageProperties();
        var message = converter.toMessage(userDTO, messageProperties);
        template.send(fanout.getName(), "", message);
    }
}
