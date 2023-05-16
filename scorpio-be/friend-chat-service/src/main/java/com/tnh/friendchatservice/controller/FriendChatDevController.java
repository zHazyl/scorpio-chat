package com.tnh.friendchatservice.controller;

import com.tnh.friendchatservice.dto.ChatProfileDTO;
import com.tnh.friendchatservice.dto.FriendChatDTO;
import com.tnh.friendchatservice.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@Slf4j
public class FriendChatDevController {

    @GetMapping("/chat-profiles/{id}")
    public ResponseEntity<ChatProfileDTO> getChatProfileById(@PathVariable("id") String id) {
        ChatProfileDTO chatProfile = new ChatProfileDTO();
        if (id.equals("05f2c145-569a-4411-804e-0b8673c13e71")) {
            chatProfile.setUserId("05f2c145-569a-4411-804e-0b8673c13e71");
            chatProfile.setFriendsRequestCode("han");
        } else {
            chatProfile.setUserId("b4984fdc-c7b3-46a7-8775-69b96409d3a3");
            chatProfile.setFriendsRequestCode("hanh");
        }
        // goi xuong db map qua chat profiledto
        log.info(chatProfile.toString());
        return ResponseEntity.ok()
                .body(chatProfile);
    }

    @GetMapping("/friends-chats")
    public ResponseEntity<List<FriendChatDTO>> getAllFriendsChats() {
        List<FriendChatDTO> friendChatList = new ArrayList<>();
        FriendChatDTO friendChat = new FriendChatDTO();
        ChatProfileDTO chatProfile = new ChatProfileDTO();
        if (SecurityUtils.getCurrentUser().equals("05f2c145-569a-4411-804e-0b8673c13e71")) {
            friendChat.setId(1L);
            friendChat.setChatWith(2L);
            chatProfile.setUserId("b4984fdc-c7b3-46a7-8775-69b96409d3a3");
            chatProfile.setFriendsRequestCode("hanh");
        } else {
            friendChat.setId(2L);
            friendChat.setChatWith(1L);
            chatProfile.setUserId("05f2c145-569a-4411-804e-0b8673c13e71");
            chatProfile.setFriendsRequestCode("han");
        }
        friendChat.setRecipient(chatProfile);
        friendChatList.add(friendChat);
        return ResponseEntity.ok()
                .body(friendChatList);
    }

    @GetMapping("/group-chats")
    public ResponseEntity<List<Object>> getAllGroupsChats() {

        return ResponseEntity.ok()
                .body(null);
    }

}
