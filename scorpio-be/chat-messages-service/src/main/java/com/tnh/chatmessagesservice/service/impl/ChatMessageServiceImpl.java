package com.tnh.chatmessagesservice.service.impl;

import com.tnh.chatmessagesservice.constant.DateConstants;
import com.tnh.chatmessagesservice.constant.MessageStatus;
import com.tnh.chatmessagesservice.document.ChatMessage;
import com.tnh.chatmessagesservice.repository.ChatMessageRepository;
import com.tnh.chatmessagesservice.service.ChatMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Slf4j
@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public Mono<ChatMessage> saveChatMessage(ChatMessage chatMessage) {
        return Mono.just(new ChatMessage())
                .flatMap(message -> {
//                    try {
//                        var localDateTime = LocalDateTime.parse(
//                                message.getTime().toString(),
//                                DateTimeFormatter.ofPattern(DateConstants.UTC_DATE_FORMAT));
//                        message.setTime(Date.from(localDateTime.toInstant(ZoneOffset.UTC)));
//                    } catch (DateTimeParseException ex) {
//
//                    }
                    message.setStatus(MessageStatus.RECEIVED);
                    message.setContent(chatMessage.getContent());
                    message.setFriendChat(chatMessage.getFriendChat());
                    message.setRecipient(chatMessage.getRecipient());
                    message.setSender(chatMessage.getSender());
                    message.setType(chatMessage.getType());
                    return Mono.just(message);
                })
                .flatMap(chatMessageRepository::save);
    }
}
