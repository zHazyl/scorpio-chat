package com.tnh.groupchatservice.service;

import com.tnh.groupchatservice.domain.ChatProfile;

public interface ChatProfileService {
    ChatProfile createChatProfile(String userId, String username);

    ChatProfile generateNewFriendsRequestCode(String userId, String username);

    ChatProfile getChatProfileById(String userId);
}
