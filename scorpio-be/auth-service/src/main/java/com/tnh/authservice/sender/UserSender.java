package com.tnh.authservice.sender;

import com.tnh.authservice.dto.UserDTO;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;


public class UserSender {

    private final RabbitTemplate template;
    private final FanoutExchange fanout;

    public UserSender(RabbitTemplate template, FanoutExchange fanout) {
        this.template = template;
        this.fanout = fanout;
    }


    public void send(UserDTO userDTO) {
        var converter = template.getMessageConverter();
        var messageProperties = new MessageProperties();
        var message = converter.toMessage(userDTO, messageProperties);
        template.send(fanout.getName(), "friend", message);
        template.send(fanout.getName(), "group", message);
    }

}
