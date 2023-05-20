package com.tnh.authservice.messaging.listener;

import com.tnh.authservice.service.implement.UserServiceImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompensateCreateUserFromGroup {
    @RabbitListener(
            queues = "#{compensateCreateUserFromGroupQueue.name}",
            messageConverter = "Jackson2JsonMessageConverter"
    )
    void compensateCreateUserFromGroup(String[] id) {
        UserServiceImplement.group.put(id[0], Integer.valueOf(id[1]));
    }

}
