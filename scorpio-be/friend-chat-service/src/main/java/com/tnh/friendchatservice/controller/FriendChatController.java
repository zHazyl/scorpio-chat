package com.tnh.friendchatservice.controller;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.domain.FriendChat;
import com.tnh.friendchatservice.domain.FriendChatRedis;
import com.tnh.friendchatservice.dto.ChatProfileDTO;
import com.tnh.friendchatservice.dto.FriendChatDTO;
import com.tnh.friendchatservice.mapper.FriendChatMapper;
import com.tnh.friendchatservice.messaging.sender.DeleteMessagesSender;
import com.tnh.friendchatservice.service.FriendChatService;
import com.tnh.friendchatservice.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// Build API
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/friends-chats")
// example : http://localhost:8081/api/v1/ is listened by this controller
public class FriendChatController {

    // Dependency injection - should read this
    private final FriendChatService friendChatService;
    private final FriendChatMapper friendChatMapper;
    private final DeleteMessagesSender deleteMessagesSender;

    @GetMapping
    public ResponseEntity<List<FriendChatDTO>> getAllFriendChats() {

        List<FriendChatRedis> friendChatRedisList = null;
        try {
            friendChatRedisList = friendChatService.getAllFriendChatsRedisBySender(
                    SecurityUtils.getCurrentUser());
            if (!friendChatRedisList.isEmpty()) {
                List<FriendChatDTO> friendChatDTOS = new ArrayList<>();
                friendChatRedisList.forEach(friendChatRedis -> {
                    FriendChatDTO friendChatDTO = new FriendChatDTO();
                    friendChatDTO.setId(friendChatRedis.getId());
                    friendChatDTO.setChatWith(friendChatRedis.getChatWith());
                    ChatProfileDTO chatProfileDTO = new ChatProfileDTO();
                    chatProfileDTO.setUserId(friendChatRedis.getRecipient());
                    friendChatDTO.setRecipient(chatProfileDTO);
                    friendChatDTOS.add(friendChatDTO);
                });
                return ResponseEntity.ok(friendChatDTOS);
            }
        } catch (Exception e) {
        }
        var allFriendsChatsBySender =
                friendChatService.getAllFriendsChatsBySender(
                        SecurityUtils.getCurrentUser());
        return ResponseEntity.ok()
                .body(friendChatMapper.mapToFriendChatList(allFriendsChatsBySender));

    }

    @PostMapping()
    public void addFriendChat(@RequestBody ChatProfile chatProfile1, ChatProfile chatProfile2) {
        friendChatService.addFriendChat(chatProfile1, chatProfile2); ;
    }
    @DeleteMapping(params = {"friend_chat", "friend_chat_with"})
    public ResponseEntity<Void> deleteUserFriendChat(@RequestParam("friend_chat") long friendChatId,
                                                     @RequestParam("friend_chat_with") long friendChatWithId) {
        friendChatService.deleteFriendChat(friendChatId, friendChatWithId, SecurityUtils.getCurrentUser());
        deleteMessagesSender.sendDeletingMessagesTask(List.of(friendChatId, friendChatWithId));
        return ResponseEntity.noContent().build();
    }


}
