package com.tnh.groupchatservice.controller;

import com.tnh.groupchatservice.dto.GroupChatDTO;
import com.tnh.groupchatservice.mapper.GroupChatMapper;
import com.tnh.groupchatservice.service.GroupChatService;
import com.tnh.groupchatservice.service.GroupMemberService;
import com.tnh.groupchatservice.utils.SecurityUtils;
import com.tnh.groupchatservice.utils.exception.InvalidDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group-chats")
public class GroupChatController {
    private final GroupChatService groupChatService;

    private final GroupMemberService groupMemberService;

    private final GroupChatMapper groupChatMapper;

    @GetMapping
    public ResponseEntity<List<GroupChatDTO>> getAllGroupsChats() {

        var allGroupsChatsByMember = groupChatService.getAllGroupsChatsByMember(SecurityUtils.getCurrentUser());

        return ResponseEntity.ok()
                .body(groupChatMapper.mapToGroupChatList(allGroupsChatsByMember));
    }

    @PostMapping
    public GroupChatDTO createGroupChat(@RequestBody GroupChatDTO groupChatDTO) {
        var group = groupChatService.createGroupChat(groupChatDTO.getGroupName());
        groupMemberService.createGroupMember(group.getId(), SecurityUtils.getCurrentUser(), true);
        return groupChatMapper.mapToGroupChatDTO(group);
    }

    @DeleteMapping(params = {"group_id"})
    public ResponseEntity<Void> deleteGroupChat(@RequestParam("group_id") long groupId) {
        if (this.groupMemberService.isAdmin(groupId, SecurityUtils.getCurrentUser())) {
            this.groupMemberService.deleteMemberByGroup(groupId);
            this.groupChatService.deleteGroupChat(groupId);
        } else {
            throw new InvalidDataException("You're not admin");
        }
        return ResponseEntity.noContent().build();
    }

}
