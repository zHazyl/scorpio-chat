package com.tnh.chatmessagesservice.service.impl;

import com.tnh.chatmessagesservice.constant.DateConstants;
import com.tnh.chatmessagesservice.constant.MessageStatus;
import com.tnh.chatmessagesservice.document.ChatMessage;
import com.tnh.chatmessagesservice.repository.ChatMessageRepository;
import com.tnh.chatmessagesservice.service.ChatMessageService;
import com.tnh.chatmessagesservice.utils.exception.InvalidDataException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public Mono<ChatMessage> saveChatMessage(Long friendChat, String sender, String recipient, String content, String time, String type) {

        return Mono.just(new ChatMessage())
                .flatMap(chatMessage -> {
//                    if (StringUtils.isEmpty(content)) {
//                        return Mono.error(new InvalidDataException("Can not save empty message"));
//                    }

                    if (friendChat == null) {
                        return Mono.error(new InvalidDataException("Can not save message with empty friend chat field"));
                    }

                    if (StringUtils.isEmpty(sender) || StringUtils.isEmpty(recipient)) {
                        return Mono.error(new InvalidDataException("Can not save message with empty sender or " +
                                "recipient"));
                    }

                    if (StringUtils.isEmpty(time)) {
                        return Mono.error(new InvalidDataException("Can not save message with empty date"));
                    }
                    try {
                        var localDateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern(DateConstants.UTC_DATE_FORMAT));
                        chatMessage.setTime(Date.from(localDateTime.toInstant(ZoneOffset.UTC)));
                    } catch (DateTimeParseException ex) {
                        return Mono.error(new InvalidDataException("Time format should be in UTC"));
                    }
                    chatMessage.setStatus(MessageStatus.RECEIVED);
                    chatMessage.setContent(content);
                    chatMessage.setFriendChat(friendChat);
                    chatMessage.setRecipient(recipient);
                    chatMessage.setSender(sender);
                    chatMessage.setType(type);
                    return Mono.just(chatMessage);
                })
                .flatMap(chatMessageRepository::save);
    }

    @Override
    public Flux<ChatMessage> findLastUsersMessagesFromTime(
            long firstUserFriendChatId,
            long secondUserFriendChatId,
            String beforeTime,
            int numberOfMessagesToFetch
    ) {
        return Mono.just(beforeTime)
                .flatMap(beforeTimeInString -> {
                    try {
                        var localDateTime = LocalDateTime.parse(
                                beforeTime,
                                DateTimeFormatter.ofPattern(DateConstants.UTC_DATE_FORMAT));
                        return Mono.just(Date.from(localDateTime.toInstant(ZoneOffset.UTC)));
                    } catch (DateTimeParseException ex) {
                        return Mono.error(new InvalidDataException("Time format should be in UTC"));
                    }
                })
                .flatMapMany(formattedDate -> {
                    var sortedByTimeDescWithSize = PageRequest.of(
                            0,
                            numberOfMessagesToFetch,
                            Sort.by("time").descending());
                    return chatMessageRepository
                            .findByTimeLessThanAndFriendChatIn(
                                    formattedDate,
                                    List.of(firstUserFriendChatId, secondUserFriendChatId),
                                    sortedByTimeDescWithSize
                            );
                });
    }

    @Override
    public Flux<ChatMessage> findLastGroupMessagesFromTime(long groupId, String beforeTime, int numberOfMessagesToFetch) {
        return Mono.just(beforeTime)
                .flatMap(beforeTimeInString -> {
                    try {
                        var localDateTime = LocalDateTime.parse(beforeTime, DateTimeFormatter.ofPattern(DateConstants.UTC_DATE_FORMAT));
                        return Mono.just(Date.from(localDateTime.toInstant(ZoneOffset.UTC)));
                    } catch (DateTimeParseException ex) {
                        return Mono.error(new InvalidDataException("Time format should be in UTC"));
                    }
                }).
                flatMapMany(formattedDate -> {
                    var sortedByTimeDescWithSize = PageRequest.of(0, numberOfMessagesToFetch, Sort.by("time").descending());
                    return chatMessageRepository
                            .findByTimeLessThanAndRecipientIn(
                                    formattedDate,
                                    Long.toString(groupId),
                                    sortedByTimeDescWithSize
                            );
                });
    }

    @Override
    public Flux<ChatMessage> getLastUserMessages(long friendChatId1, long friendChatId2, int numberOfMessagesToFetch) {
        var sortedByTimeDescWithSize = PageRequest.of(0, numberOfMessagesToFetch, Sort.by("time").descending());
        return chatMessageRepository.findByFriendChatOrFriendChat(friendChatId1, friendChatId2, sortedByTimeDescWithSize);
    }

    @Override
    public Flux<ChatMessage> getLastGroupMessages(long groupId, int numberOfMessagesToFetch) {
        var sortedByTimeDescWithSize = PageRequest.of(0, numberOfMessagesToFetch, Sort.by("time").descending());
        return chatMessageRepository.findByRecipient(Long.toString(groupId), sortedByTimeDescWithSize);
    }

    @Override
    public Mono<Void> setDeliveredStatusForAllRecipientMessagesInFriendChat(long friendChatId, String currentUser) {
        return chatMessageRepository.findByFriendChatAndRecipientAndStatus(friendChatId, currentUser, MessageStatus.RECEIVED)
                .doOnNext(chatMessage -> chatMessage.setStatus(MessageStatus.DELIVERED))
                .flatMap(chatMessageRepository::save)
                .then();
    }

    @Override
    public Mono<Void> removeMessagesByFriendChat(List<Long> ids) {
        return chatMessageRepository.deleteByFriendChatIn(ids);
    }


}
