package com.tnh.friendchatservice.service;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.domain.FriendChat;

import java.util.List;

// Handle behavior in database
public interface FriendChatService {
    public void addFriendChat(ChatProfile firstUserChatProfile, ChatProfile secondUserChatProfile);
    public List<FriendChat> getAllFriendsChatsBySender(String currentUserId)
    public void deleteFriendChat(long friendChatId, long friendChatWithId, String currentUserId);

}
