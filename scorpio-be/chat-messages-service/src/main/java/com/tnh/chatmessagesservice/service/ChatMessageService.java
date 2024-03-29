package com.tnh.chatmessagesservice.service;

import com.tnh.chatmessagesservice.document.ChatMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ChatMessageService {
    Mono<ChatMessage> saveChatMessage(
            Long friendChat, String sender, String recipient, String content, String time, String type
    );

    Flux<ChatMessage> findLastUsersMessagesFromTime(
            long firstUserFriendChatId,
            long secondUserFriendChatId,
            String beforeTime,
            int numberOfMessagesToFetch
    );

    Flux<ChatMessage> findLastGroupMessagesFromTime(
            long groupId,
            String beforeTime,
            int numberOfMessagesToFetch
    );

    Flux<ChatMessage> getLastUserMessages(
            long friendChatId1, long friendChatId2, int numberOfMessagesToFetch
    );

    Flux<ChatMessage> getLastGroupMessages(long groupId, int numberOfMessagesToFetch);

    Mono<Void> setDeliveredStatusForAllRecipientMessagesInFriendChat(
            long friendChatId, String currentUser
    );

    Mono<Void> removeMessagesByFriendChat(List<Long> ids);

}
