package com.tnh.friendchatservice.repository;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.domain.FriendChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


// Handle behavior in database
@Repository
public interface FriendChatRepository extends JpaRepository<FriendChat, Long> {

    @Query("SELECT " +
            "CASE WHEN COUNT (*) > 0 THEN true ELSE false END " +
            "FROM FriendChat fc WHERE (fc.sender = :chatProfile1 AND fc.recipient = :chatProfile2) " +
            "OR (fc.sender = :chatProfile2 AND fc.recipient = :chatProfile1)")
    boolean existsFriendChatForUsers(ChatProfile chatProfile1, ChatProfile chatProfile2);
    @Query("SELECT fc from FriendChat fc WHERE fc.id = :friendChatId " +
            "AND fc.chatWith.id = :friendChatWithId " +
            "AND fc.sender.userId = :senderId")
    Optional<FriendChat> findByIdAndFriendChatWithIdAndSenderId(long friendChatId,
                                                                long friendChatWithId,
                                                                String senderId);

    List<FriendChat> findBySender(ChatProfile sender);

}
