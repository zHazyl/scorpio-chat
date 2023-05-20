package com.tnh.groupchatservice.messaging.listener;

import com.tnh.groupchatservice.dto.UserDTO;
import com.tnh.groupchatservice.service.ChatProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitNewUserListener {

    private final ChatProfileService chatProfileService;

    public RabbitNewUserListener(ChatProfileService chatProfileService) {
        this.chatProfileService = chatProfileService;
    }


//    @RabbitListener(queues = "#{newUsersQueue.name}", messageConverter = "Jackson2JsonMessageConverter")
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "com.tnh.authservice.fanout", type = "fanout"),
            key = "group",
            value = @Queue("#{newUsersQueue.name}")),
//            queues = "#{newUsersQueue.name}",
            messageConverter = "Jackson2JsonMessageConverter"
    )
    public void receiveNewUser(UserDTO userDTO) {
        log.debug("New user {}", userDTO.getUsername());
        chatProfileService.createChatProfile(userDTO.getId(), userDTO.getUsername());
    }


}
