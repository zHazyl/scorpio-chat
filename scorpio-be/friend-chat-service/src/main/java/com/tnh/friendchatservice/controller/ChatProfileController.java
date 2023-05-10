package com.tnh.friendchatservice.controller;

import com.tnh.friendchatservice.dto.ChatProfileDTO;
import com.tnh.friendchatservice.mapper.ChatProfileMapper;
import com.tnh.friendchatservice.service.ChatProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat-profiles")
public class ChatProfileController {

    private final ChatProfileService chatProfileService;
    private final ChatProfileMapper chatProfileMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ChatProfileDTO> getChatProfileById(@PathVariable("id") String id) {
        return ResponseEntity.ok()
                .body(chatProfileMapper.chatProfileToChatProfileDTO(chatProfileService.getChatProfileById(id)));
    }

//    @PatchMapping("/{id}/new-friends-request-code")
//    public ResponseEntity<ChatProfileDTO> generateNewFriendsRequestCode(@PathVariable("id") String userId) {
//        if (!userId.equals(SecurityUtils.getCurrentUser())) {
//            throw new InvalidDataException("Invalid user id");
//        }
//        var chatProfile = chatProfileService.generateNewFriendsRequestCode(userId, SecurityUtils.getCurrentUserPreferredUsername());
//        return ResponseEntity.ok()
//                .body(chatProfileMapper.chatProfileToChatProfileDTO(chatProfile));
//    }


}
