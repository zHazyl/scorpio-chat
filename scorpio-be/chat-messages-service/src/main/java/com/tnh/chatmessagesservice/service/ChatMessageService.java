package com.tnh.chatmessagesservice.service;

import com.tnh.chatmessagesservice.document.ChatMessage;
import reactor.core.publisher.Mono;

public interface ChatMessageService {
    Mono<ChatMessage> saveChatMessage(ChatMessage chatMessage);

}
