package com.tnh.friendchatservice.controller;

import com.tnh.friendchatservice.domain.ChatProfile;
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
            return null; //friendChatService.getAllFriendChats();
        }
        else
            return null;
    }

    @PostMapping
    public void addFriendChat(ChatProfile chatProfile1, ChatProfile chatProfile2) {
        friendChatService.addFriendChat(chatProfile1, chatProfile2); ;
    }
    @DeleteMapping("/{friendChatId/friendChatWithId/currentUserId}")
    public void deleteFriendChat(long friendChatId, long friendChatWithId, String currentUserId)
    {
        friendChatService.deleteFriendChat(friendChatId,friendChatWithId,currentUserId);
    }


}
