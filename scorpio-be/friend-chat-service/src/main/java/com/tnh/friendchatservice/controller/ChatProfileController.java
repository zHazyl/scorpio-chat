package com.tnh.friendchatservice.controller;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.service.ChatProfileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatProfileController {
    private final ChatProfileService chatProfileService;

    public ChatProfileController(ChatProfileService chatProfileService) {
        this.chatProfileService = chatProfileService;
    }
    @GetMapping
    public List<ChatProfile> getAllProfile() {
        return chatProfileService.getALlProfie();
    }
}
