package com.tnh.friendchatservice.controller;

import com.tnh.friendchatservice.domain.FriendChat;
import com.tnh.friendchatservice.service.FriendChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Build API
@RestController
@RequestMapping("/api/v2/FriendChat")
// example : http://localhost:8081/api/v2/FriendChat is listened by this controller
public class FriendChatController {

    // Dependency injection - should read this
    private final FriendChatService friendChatService;

    public FriendChatController(FriendChatService friendChatService) {
        this.friendChatService = friendChatService;
    }

    @GetMapping
    public List<FriendChat> getAllFriendChats() {
        return friendChatService.getAllFriendChats();
    }

    @PostMapping
    public FriendChat addFriendChat(@RequestBody FriendChat friendChat) {
        return friendChatService.addFriendChat(friendChat);
    }


}
