package com.tnh.friendchatservice.service.impl;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.repository.ChatProfileRepository;
import com.tnh.friendchatservice.service.ChatProfileService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ChatProfileServiceImpl implements ChatProfileService {

    private final ChatProfileRepository chatProfileRepository;

    public ChatProfileServiceImpl(ChatProfileRepository chatProfileRepository) {
        this.chatProfileRepository = chatProfileRepository;
    }
    @Override
    public List<ChatProfile> getALlProfie() {
        return chatProfileRepository.findAll();
    }
}
