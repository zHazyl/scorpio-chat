package com.tnh.friendchatservice.repository;

import com.tnh.friendchatservice.domain.ChatProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ChatProfileRepository extends JpaRepository<ChatProfile, String> {
}
