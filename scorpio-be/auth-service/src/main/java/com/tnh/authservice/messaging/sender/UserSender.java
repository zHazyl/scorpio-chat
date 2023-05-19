package com.tnh.authservice.messaging.sender;

import com.tnh.authservice.config.KeycloakProvider;
import com.tnh.authservice.dto.UserDTO;
import com.tnh.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@RequiredArgsConstructor
public class UserSender {

    private final RabbitTemplate template;
    private final FanoutExchange fanout;


    public void send(UserDTO userDTO) {
        var converter = template.getMessageConverter();
        var messageProperties = new MessageProperties();
        var message = converter.toMessage(userDTO, messageProperties);
        template.send(fanout.getName(), "", message);
    }


}
