package com.tnh.groupchatservice.service.impl;

import com.tnh.groupchatservice.domain.ChatProfile;
import com.tnh.groupchatservice.domain.GroupMember;
import com.tnh.groupchatservice.repository.GroupChatRepository;
import com.tnh.groupchatservice.repository.GroupMemberRepository;
import com.tnh.groupchatservice.service.ChatProfileService;
import com.tnh.groupchatservice.service.GroupMemberService;
import com.tnh.groupchatservice.utils.exception.AlreadyExistsException;
import com.tnh.groupchatservice.utils.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupMemberRepository groupMemberRepository;

    private final GroupChatRepository groupChatRepository;

    private final ChatProfileService chatProfileService;

    @Override
    public void createGroupMember(Long group, String member, boolean isAdmin) {
        ChatProfile memberProfile = null;

        try {
            memberProfile = chatProfileService.getChatProfileById(member);
        } catch (Exception e) {
            throw new NotFoundException("Chat profile not found.");
        }
        var groupMember = new GroupMember();
        groupMember.setGroup(groupChatRepository.getGroupChatById(group));

        if (groupMemberRepository.existsGroupMemberByGroupAndMember(
                groupMember.getGroup(), groupMember.getMember())) {
            throw new AlreadyExistsException("This person has already been member");
        }

        groupMember.setMember(memberProfile);
        groupMember.setAdmin(isAdmin);

        groupMemberRepository.save(groupMember);

    }

    @Override
    public List<GroupMember> getAllGroupsMembersByGroup(Long group) {
        try{
            return this.groupMemberRepository.findByGroup(
                    groupChatRepository.getGroupChatById(group));
        } catch (Exception e) {
            throw new NotFoundException("This group is not found");
        }
    }

    @Override
    public List<GroupMember> getAllGroupsMembersByMember(String currentUser) {
        try{
            return this.groupMemberRepository.findByMember(
                    chatProfileService.getChatProfileById(currentUser));
        } catch (Exception e) {
            throw new NotFoundException("This user " + currentUser + " is not found");
        }
    }

    @Override
    public boolean isAdmin(Long group, String member) {
        var mem = groupMemberRepository.findGroupMemberByGroupAndMember(
                groupChatRepository.getGroupChatById(group),
                chatProfileService.getChatProfileById(member));
        return mem.isAdmin();
    }

    @Override
    public void deleteMember(long groupId, String memberId) {
        this.groupMemberRepository.deleteGroupMemberByGroupAndMember(
                groupChatRepository.getGroupChatById(groupId),
                chatProfileService.getChatProfileById(memberId));
    }

    @Override
    public void deleteMemberByGroup(long groupId) {
        this.groupMemberRepository.deleteGroupMemberByGroup(
                groupChatRepository.getGroupChatById(groupId));
    }
}
