package com.tnh.chatmessagesservice.controller;

import com.tnh.chatmessagesservice.document.ChatMessage;
import com.tnh.chatmessagesservice.service.ChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/chat-messages")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @PostMapping
    public Mono<ChatMessage> send(@RequestBody ChatMessage chatMessage) {
        log.info(chatMessage.getTime().toString());
        return chatMessageService.saveChatMessage(chatMessage);
    }
}
