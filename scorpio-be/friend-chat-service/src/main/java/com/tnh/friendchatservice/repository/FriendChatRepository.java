package com.tnh.friendchatservice.repository;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.domain.FriendChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


// Handle behavior in database
@Repository
public interface FriendChatRepository extends JpaRepository<FriendChat, Long> {

    @Query("SELECT " +
            "CASE WHEN COUNT (*) > 0 THEN true ELSE false END " +
            "FROM FriendChat fc WHERE (fc.sender_id = :chatProfile1 AND fc.recipient_id = :chatProfile2) " +
            "OR (fc.sender_id = :chatProfile2 AND fc.recipient_id = :chatProfile1)")
    boolean existsFriendChatForUsers(ChatProfile chatProfile1, ChatProfile chatProfile2);
    @Query("SELECT fc from FriendChat fc WHERE fc.id = :friendChatId " +
            "AND fc.chat_with_id.id = :friendChatWithId " +
            "AND fc.sender_id.userId = :senderId")
    Optional<FriendChat> findByIdAndFriendChatWithIdAndSenderId(long friendChatId,
                                                                long friendChatWithId,
                                                                UUID senderId);
    List<FriendChat> findBySender(ChatProfile sender);

}
