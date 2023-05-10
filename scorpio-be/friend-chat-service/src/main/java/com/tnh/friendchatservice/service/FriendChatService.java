package com.tnh.friendchatservice.service;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.domain.FriendChat;
import com.tnh.friendchatservice.domain.FriendChatRedis;

import java.util.List;

// Handle behavior in database
public interface FriendChatService {
    void addFriendChat(ChatProfile firstUserChatProfile, ChatProfile secondUserChatProfile);
    List<FriendChat> getAllFriendsChatsBySender(String currentUserId);
    void deleteFriendChat(long friendChatId, long friendChatWithId, String currentUserId);
    List<FriendChatRedis> getAllFriendChatsRedisBySender(String currentUser);

}
