package com.tnh.authservice.messaging.listener;

import com.tnh.authservice.messaging.sender.UserSender;
import com.tnh.authservice.service.UserService;
import com.tnh.authservice.service.implement.UserServiceImplement;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompensateCreateUserFromFriend {

    @RabbitListener(
            queues = "#{compensateCreateUserFromFriendQueue.name}",
            messageConverter = "Jackson2JsonMessageConverter"
    )
    void compensateCreateUserFromFriend(String[] id) {
        UserServiceImplement.friend.put(id[0], Integer.valueOf(id[1]));
    }

}
