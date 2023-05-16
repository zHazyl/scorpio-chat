package com.tnh.groupchatservice.repository;

import com.tnh.groupchatservice.domain.GroupChat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {
    GroupChat getGroupChatById(Long id);
    void deleteGroupChatById(Long id);
}
