package com.tnh.friendchatservice.service;

import com.tnh.friendchatservice.domain.FriendChat;

import java.util.List;

// Handle behavior in database
public interface FriendChatService {
    FriendChat addFriendChat(FriendChat friendChat);
    List<FriendChat> getAllFriendChats();
    String deleteFriendChat(long id);

}
