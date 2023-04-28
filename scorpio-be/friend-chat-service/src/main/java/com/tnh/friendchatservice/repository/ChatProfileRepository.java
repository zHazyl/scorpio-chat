package com.tnh.friendchatservice.repository;

import com.tnh.friendchatservice.domain.FriendChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// Handle behavior in database
@Repository
public interface ChatProfileRepository extends JpaRepository<FriendChat, String> {

}
