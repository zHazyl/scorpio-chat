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

    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = "com.tnh.authservice.fanout", type = "fanout"),
            key = "voting",
            value = @Queue("#{newUsersQueue.name}")),
//            queues = "#{newUsersQueue.name}",
            messageConverter = "Jackson2JsonMessageConverter"
    )
    public void voting(UserDTO userDTO) {
        String[] id = new String[2];
        id[0] = userDTO.getId();
        try {
            chatProfileService.getChatProfileById(userDTO.getId());
            id[1] = String.valueOf(-1);
            compensateNewUser.sendCompensate(id);
        } catch (Exception e) {
            id[1] = String.valueOf(1);
        }
    }

}
