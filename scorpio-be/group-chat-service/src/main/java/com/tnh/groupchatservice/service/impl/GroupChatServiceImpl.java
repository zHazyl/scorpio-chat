package com.tnh.groupchatservice.service.impl;

import com.tnh.groupchatservice.domain.GroupChat;
import com.tnh.groupchatservice.repository.GroupChatRepository;
import com.tnh.groupchatservice.service.GroupChatService;
import com.tnh.groupchatservice.service.GroupMemberService;
import com.tnh.groupchatservice.utils.exception.InvalidDataException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupChatServiceImpl implements GroupChatService {

    private final GroupChatRepository groupChatRepository;
    
    private final GroupMemberService groupMemberService;

    @Override
    public GroupChat createGroupChat(String name) {

//        if (!StringUtils.isNotBlank(userId)) {
//            throw new InvalidDataException("Chat profile can't be created because user id is empty.");
//        }
//        if (!StringUtils.isNotBlank(username)) {
//            throw new InvalidDataException("Chat profile can't be created because username is empty.");
//        }
//
//        var chatProfile = new ChatProfile();
//        chatProfile.setUserId(UUID.fromString(userId));
//        chatProfile.setFriendsRequestCode(generateFriendRequestCode(username));
//        return chatProfileRepository.save(chatProfile);

        if (!StringUtils.isNotBlank(name)) {
            throw new InvalidDataException("Group chat can't be created because name is empty");
        }

        var groupChat = new GroupChat();
        groupChat.setGroupName(name);
        groupChat.setCreatedDate(OffsetDateTime.now());

        return this.groupChatRepository.save(groupChat);

    }

    @Override
    public GroupChat getGroupChatById(Long id) {
        return groupChatRepository.getGroupChatById(id);
    }

    @Override
    public List<GroupChat> getAllGroupsChatsByMember(String currentUser) {

        var groupsMembers = groupMemberService.getAllGroupsMembersByMember(currentUser);
        List<GroupChat> groups = new ArrayList<>();

        for (var mem : groupsMembers) {
            groups.add(mem.getGroup());
        }

        return groups;
    }

    @Override
    public void deleteGroupChat(Long id) {
        this.groupChatRepository.deleteGroupChatById(id);
    }
}
