package com.tnh.friendchatservice.controller;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.domain.FriendRequest;
import com.tnh.friendchatservice.repository.ChatProfileRepository;
import com.tnh.friendchatservice.repository.FriendRequestRepository;
import com.tnh.friendchatservice.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendrequest")
public class FriendRequestController {
    private final FriendRequestService friendRequestService;
    @Autowired
    public FriendRequestController(FriendRequestService friendRequestService)
    {
        this.friendRequestService = friendRequestService;
    }
    @GetMapping()
    public List<FriendRequest> getAllRequest (){
        return friendRequestService.getAllRequest();
    }
    @GetMapping(value = "/sent/{id}")
    public ResponseEntity<?> getAllUnacceptedRequestBySenderId (@PathVariable("id") String sender_id ){
        List<FriendRequest> friendRequestListBySender = friendRequestService.getAllUnacceptedRequestBySenderId(sender_id);
        return ResponseEntity.ok(friendRequestListBySender);
    }
    @GetMapping(value = "/received/{id}")
    public ResponseEntity<?> getAllUnacceptedRequestToRecipientId (@PathVariable("id") String sender_id ) {
        List<FriendRequest> friendRequestList = friendRequestService.getAllUnacceptedRequestToSenderId(sender_id);
        return ResponseEntity.ok(friendRequestList);
    }
    @PostMapping(value = "/invite")
    public ResponseEntity<?> createFriendRequest(@RequestParam String sender_id, String friend_code_request)
    {
        FriendRequest newFriendRequest = friendRequestService.createFriendRequest(sender_id,friend_code_request);
        return ResponseEntity.ok(newFriendRequest);
    }





}
