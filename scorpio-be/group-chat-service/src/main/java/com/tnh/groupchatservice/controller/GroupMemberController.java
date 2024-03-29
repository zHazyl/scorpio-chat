package com.tnh.groupchatservice.controller;

import com.tnh.groupchatservice.dto.GroupMemberDTO;
import com.tnh.groupchatservice.mapper.GroupMemberMapper;
import com.tnh.groupchatservice.service.GroupMemberService;
import com.tnh.groupchatservice.utils.SecurityUtils;
import com.tnh.groupchatservice.utils.exception.InvalidDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/group-member")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;
    private final GroupMemberMapper groupMemberMapper;

    @GetMapping("/{id}")
    public ResponseEntity<List<GroupMemberDTO>> getGroupsMembersById(@PathVariable("id") Long id) {
        return ResponseEntity.ok()
                .body(groupMemberMapper.mapToGroupMemberList(groupMemberService.getAllGroupsMembersByGroup(id)));
    }

    @PostMapping
    public ResponseEntity<List<GroupMemberDTO>> addMember(@RequestBody List<GroupMemberDTO> members) {
        var id = members.get(0).getGroup().getId();
        if (groupMemberService.isAdmin(id, SecurityUtils.getCurrentUser())) {
            for (var mem: members) {
                groupMemberService.createGroupMember(id, mem.getMember().getUserId(), mem.isAdmin());
            }
            return ResponseEntity.ok()
                    .body(groupMemberMapper.mapToGroupMemberList(groupMemberService.getAllGroupsMembersByGroup(id)));
        }

        throw new InvalidDataException("You're not admin");

    }

    @DeleteMapping(params = {"group_id", "member_id"})
    public ResponseEntity<Void> deleteUserFriendChat(@RequestParam("group_id") long groupId,
                                                     @RequestParam("member_id") String memberId) {
        if (this.groupMemberService.isAdmin(groupId, SecurityUtils.getCurrentUser())
        || memberId == SecurityUtils.getCurrentUser()) {
            groupMemberService.deleteMember(groupId, memberId);
        } else {
            throw new InvalidDataException("You're not admin");
        }
//        deleteMessagesSender.sendDeletingMessagesTask(List.of(friendChatId, friendChatWithId));
        return ResponseEntity.noContent().build();
    }


}
