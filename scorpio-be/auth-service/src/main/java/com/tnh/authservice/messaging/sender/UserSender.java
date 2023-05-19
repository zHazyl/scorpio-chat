package com.tnh.authservice.messaging.sender;

import com.tnh.authservice.config.KeycloakProvider;
import com.tnh.authservice.dto.UserDTO;
import com.tnh.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
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
    private final KeycloakProvider keycloakProvider;
    private final UserService userService;
    public static Map<String, Integer> friend = new HashMap<>();
    public static Map<String, Integer> group = new HashMap<>();

    public void send(UserDTO userDTO) throws InterruptedException {
        var converter = template.getMessageConverter();
        var messageProperties = new MessageProperties();
        var message = converter.toMessage(userDTO, messageProperties);
        template.send(fanout.getName(), "voting", message);
        String id = userDTO.getId();
        friend.put(id, 0);
        group.put(id, 0);
        ExecutorService threadpool = Executors.newCachedThreadPool();
        threadpool.awaitTermination(5, TimeUnit.SECONDS);
        threadpool.submit(() -> {
            int count = 0;
            while (count <= 3) {
                if (friend.get(id) == 1 && group.get(id) == 1) {
                    userService.createUser(id, userDTO.getUsername(), userDTO.getPassword(),
                    userDTO.getEmail(), userDTO.getFirstName(), userDTO.getLastName());
                    template.send(fanout.getName(), "friend", message);
                    template.send(fanout.getName(), "group", message);
                    break;
                }
                if (friend.get(id) == -1 && group.get(id) == -1 || count == 3) {
                    keycloakProvider.getInstance().realm(keycloakProvider.getRealm()).users().get(id).remove();
                    break;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                    count++;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


}
