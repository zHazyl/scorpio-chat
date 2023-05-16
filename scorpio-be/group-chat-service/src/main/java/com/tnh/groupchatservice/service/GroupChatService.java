package com.tnh.groupchatservice.service;

import com.tnh.groupchatservice.domain.GroupChat;

import java.util.List;

public interface GroupChatService {
    GroupChat createGroupChat(String name);

    GroupChat getGroupChatById(Long id);

    List<GroupChat> getAllGroupsChatsByMember(String currentUser);

    void deleteGroupChat(Long id);
}
