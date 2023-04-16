package com.tnh.friendchatservice.controller;

import com.tnh.friendchatservice.domain.FriendChat;
import com.tnh.friendchatservice.service.FriendChatService;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// Build API
@RestController
@NoArgsConstructor
@RequestMapping("/api/v1/FriendChat")
// example : http://localhost:8081/api/v1/members is listened by this controller
public class FriendChatController {

    // Dependency injection - should read this
    private FriendChatService friendChatService;

    public FriendChatController(FriendChatService friendChatService) {
        this.friendChatService = friendChatService;
    }

    @GetMapping
    public List<FriendChat> getAllFriendChats() {
        if(this.friendChatService != null)
        {
            return friendChatService.getAllFriendChats();
        }
        else
            return null;
    }

//    @PostMapping
//    public FriendChat addFriendChat() {
//
//    }
//    @DeleteMapping("/{id}")
//    public String deleteFriendChat()
//    {
//    }


}
