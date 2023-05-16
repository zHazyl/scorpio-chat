package com.tnh.groupchatservice.repository;

import com.tnh.groupchatservice.domain.ChatProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// Handle behavior in database
@Repository
public interface ChatProfileRepository extends JpaRepository<ChatProfile, String> {
    Optional<ChatProfile> findByFriendsRequestCode(String friendsRequestCode);
}
