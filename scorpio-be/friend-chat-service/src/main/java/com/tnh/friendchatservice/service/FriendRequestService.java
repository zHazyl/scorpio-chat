package com.tnh.friendchatservice.service;

import com.tnh.friendchatservice.domain.FriendRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FriendRequestService {
     List<FriendRequest> getAllUnacceptedRequestBySenderId(String sender_id);
     List<FriendRequest> getAllUnacceptedRequestToSenderId(String sender_id);

     List<FriendRequest> getAllRequest();
     FriendRequest createFriendRequest(String sender_id, String friend_request_code);

     void deleteFriendRequest(String sender_id, long friendRequestId);

     FriendRequest deleteFriendRequest();

}
