package com.tnh.friendchatservice.service;

import com.tnh.friendchatservice.domain.ChatProfile;

public interface ChatProfileService {
    ChatProfile createChatProfile(String userId, String username);

    ChatProfile generateNewFriendsRequestCode(String userId, String username);

    ChatProfile getChatProfileById(String userId);
}
