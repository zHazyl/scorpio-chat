package com.tnh.groupchatservice.service.impl;

import com.tnh.groupchatservice.domain.ChatProfile;
import com.tnh.groupchatservice.repository.ChatProfileRepository;
import com.tnh.groupchatservice.service.ChatProfileService;
import com.tnh.groupchatservice.utils.exception.InvalidDataException;
import com.tnh.groupchatservice.utils.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatProfileServiceImpl implements ChatProfileService {

    private final ChatProfileRepository chatProfileRepository;

    @Override
    public ChatProfile createChatProfile(String userId, String username) {

        if (!StringUtils.isNotBlank(userId)) {
            throw new InvalidDataException("Chat profile can't be created because user id is empty.");
        }
        if (!StringUtils.isNotBlank(username)) {
            throw new InvalidDataException("Chat profile can't be created because username is empty.");
        }

        var chatProfile = new ChatProfile();
        chatProfile.setUserId(userId);
        chatProfile.setFriendsRequestCode(generateFriendRequestCode(username));
        return chatProfileRepository.save(chatProfile);
    }

    @Override
    public ChatProfile generateNewFriendsRequestCode(String userId, String username) {
        var chatProfile = getChatProfileById(userId);
        chatProfile.setFriendsRequestCode(generateFriendRequestCode(username));
        return chatProfileRepository.save(chatProfile);
    }

    private String generateFriendRequestCode(String username) {
        return username.toLowerCase() + "-" + RandomStringUtils.randomNumeric(10);
    }

    @Override
    public ChatProfile getChatProfileById(String userId) {
        return chatProfileRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Not found chat profile for user " + userId));
    }


}
