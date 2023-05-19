package com.tnh.authservice.messaging.listener;

import com.tnh.authservice.messaging.sender.UserSender;
import com.tnh.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompensateCreateUserFromFriend {
    private final UserService userService;

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "com.tnh.authservice.fanout", type = "fanout"),
            key = "friend-compensate",
            value = @Queue("#{compensateCreateUserFromFriendQueue.name}")),
            messageConverter = "Jackson2JsonMessageConverter"
    )
    void compensateCreateUserFromFriend(String[] id) {
        UserSender.friend.put(id[0], Integer.valueOf(id[1]));
    }

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "com.tnh.authservice.fanout", type = "fanout"),
            key = "group-compensate",
            value = @Queue("#{compensateCreateUserFromFriendQueue.name}")),
            messageConverter = "Jackson2JsonMessageConverter"
    )
    void compensateCreateUserFromGroup(String[] id) {
        UserSender.group.put(id[0], Integer.valueOf(id[1]));
    }
}
