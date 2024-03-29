package com.tnh.groupchatservice.messaging.listener;

import com.tnh.groupchatservice.dto.UserDTO;
import com.tnh.groupchatservice.messaging.sender.CompensateNewUser;
import com.tnh.groupchatservice.service.ChatProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitNewUserListener {

    private final ChatProfileService chatProfileService;
    private final CompensateNewUser compensateNewUser;


    @RabbitListener(queues = "#{newUsersQueue.name}", messageConverter = "Jackson2JsonMessageConverter")
    public void receiveNewUser(UserDTO userDTO) {
        log.debug("New user {}", userDTO.getUsername());
        chatProfileService.createChatProfile(userDTO.getId(), userDTO.getUsername());
    }

}
