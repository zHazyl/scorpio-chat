package com.tnh.groupchatservice.service;

import com.tnh.groupchatservice.domain.GroupMember;

import java.util.List;

public interface GroupMemberService {
    void createGroupMember(Long group, String member, boolean isAdmin);

    List<GroupMember> getAllGroupsMembersByGroup(Long group);

    List<GroupMember> getAllGroupsMembersByMember(String currentUser);

    boolean isAdmin(Long group, String member);

    void deleteMember(long groupId, String memberId);

    void deleteMemberByGroup(long groupId);
}
