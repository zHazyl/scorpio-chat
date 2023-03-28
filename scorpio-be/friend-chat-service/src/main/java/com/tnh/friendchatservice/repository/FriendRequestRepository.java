package com.tnh.friendchatservice.repository;

import com.tnh.friendchatservice.domain.FriendRequest;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    @Query("SELECT freq FROM FriendRequest freq WHERE freq.sender_id = :sender_id AND freq.is_accepted = false")
    List<FriendRequest> getAllUnacceptedRequestBySenderId (String sender_id);

    @Query("SELECT freq FROM FriendRequest freq WHERE freq.recipient_id = :sender_id AND freq.is_accepted = false ")
    List<FriendRequest> getAllUnacceptedRequestToSenderId (String sender_id);

    // check if friend request already exists
    @Query("SELECT COUNT(*) " +
            "FROM FriendRequest freq " +
            "WHERE (freq.sender_id = :sender_id AND freq.recipient_id = :recipient_id) OR " +
            "(freq.sender_id = :recipient_id AND freq.recipient_id = :sender_id) AND " +
            "freq.is_accepted = false")
    int checkFriendRequestExist(String sender_id, String recipient_id);

    @Modifying
    @Query(value = "INSERT INTO friend_request (sender_id, recipient_id, sent_time, is_accepted) " +
            "VALUES (:sender_id, :recipient_id, NOW(), false)", nativeQuery = true)
    void addFriendRequest(String sender_id, String recipient_id);




}
