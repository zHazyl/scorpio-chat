package com.tnh.groupchatservice.messaging.listener;

import com.tnh.groupchatservice.dto.UserDTO;
import com.tnh.groupchatservice.messaging.sender.CompensateNewUser;
import com.tnh.groupchatservice.service.ChatProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VotingListener {

    private final ChatProfileService chatProfileService;
    private final CompensateNewUser compensateNewUser;

    @RabbitListener(
            queues = "#{votingQueue.name}",
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
            compensateNewUser.sendCompensate(id);
        }
    }

}
