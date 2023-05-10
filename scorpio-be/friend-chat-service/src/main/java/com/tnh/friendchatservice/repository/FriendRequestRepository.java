package com.tnh.friendchatservice.repository;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.domain.FriendRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    @Query("SELECT " +
            "CASE WHEN COUNT(fr) > 0 THEN true ELSE false END " +
            "FROM FriendRequest fr WHERE (fr.sender = :user1 AND fr.recipient = :user2) " +
            "OR (fr.sender = :user2 AND fr.recipient = :user1)")
    boolean isFriendRequestAlreadyExists(ChatProfile user1, ChatProfile user2);

    @Query("SELECT fr FROM FriendRequest fr WHERE fr.recipient.userId = :recipientId AND fr.isAccepted = false")
    List<FriendRequest> findAllByRecipientIdAndNotAccepted(String recipientId);

    @Query("SELECT fr FROM FriendRequest fr WHERE fr.sender.userId = :senderId AND fr.isAccepted = false")
    List<FriendRequest> findAllBySenderIdAndNotAccepted(String senderId);

    @Modifying
    @Query("DELETE FROM FriendRequest WHERE (sender = :sender AND recipient = :recipient) " +
            "OR (sender = :recipient AND recipient = :sender)")
    void deleteFriendRequestByChatProfiles(ChatProfile sender, ChatProfile recipient);

}
