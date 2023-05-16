package com.tnh.groupchatservice.repository;

import com.tnh.groupchatservice.domain.ChatProfile;
import com.tnh.groupchatservice.domain.GroupChat;
import com.tnh.groupchatservice.domain.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {

    List<GroupMember> findByGroup(GroupChat group);

    List<GroupMember> findByMember(ChatProfile member);

//    @Query("SELECT " +
//            "CASE WHEN COUNT(fr) > 0 THEN true ELSE false END " +
//            "FROM FriendRequest fr WHERE (fr.sender = :user1 AND fr.recipient = :user2) " +
//            "OR (fr.sender = :user2 AND fr.recipient = :user1)")
//    boolean isFriendRequestAlreadyExists(ChatProfile user1, ChatProfile user2);

    boolean existsGroupMemberByGroupAndMember(GroupChat group, ChatProfile member);

    GroupMember findGroupMemberByGroupAndMember(GroupChat groupChat, ChatProfile member);

    void deleteGroupMemberByGroupAndMember(GroupChat groupChat, ChatProfile member);

    void deleteGroupMemberByGroup(GroupChat groupChat);

}
