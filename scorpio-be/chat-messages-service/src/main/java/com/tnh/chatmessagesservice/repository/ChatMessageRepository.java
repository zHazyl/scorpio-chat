package com.tnh.chatmessagesservice.repository;

import com.tnh.chatmessagesservice.document.ChatMessage;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ChatMessageRepository extends ReactiveCrudRepository<ChatMessage, String> {
}
