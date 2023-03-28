package com.tnh.friendchatservice.service.impl;

import com.tnh.friendchatservice.domain.FriendChat;
import com.tnh.friendchatservice.repository.FriendChatRepository;
import com.tnh.friendchatservice.service.FriendChatService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FriendChatServiceImpl implements FriendChatService {

    // Dependency injection - should read this
    private final FriendChatRepository friendChatRepository;

    public FriendChatServiceImpl(FriendChatRepository friendChatRepository) {
        this.friendChatRepository = friendChatRepository;
    }

    @Override
    public FriendChat addFriendChat(FriendChat friendChat) {
        return friendChatRepository.save(friendChat);
    }
    @Override
    public List<FriendChat> getAllFriendChats() {
        return friendChatRepository.findAll();
    }
}
