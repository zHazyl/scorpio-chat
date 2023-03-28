package com.tnh.friendchatservice.service.impl;

import com.tnh.friendchatservice.domain.FriendRequest;
import com.tnh.friendchatservice.repository.FriendRequestRepository;
import com.tnh.friendchatservice.service.FriendRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestImpl implements FriendRequestService{

    private FriendRequestRepository friendRequestRepository;
    @Autowired
    public  FriendRequestImpl (FriendRequestRepository freq){
        this.friendRequestRepository = freq;
    }

    @Override
    public List<FriendRequest> getAllUnacceptedRequestBySenderId(String sender_id) {

        /*List<FriendRequestDTO> friendRequestDTOList = new ArrayList<FriendRequestDTO>();
        List<FriendRequest> temp = friendRequestRepository.findAll();
        for (FriendRequest req : temp){
            friendRequestDTOList.add(FriendRequestMapper.toFriendRequestDTO(req));
        }*/
        /*List<FriendRequestDTO> friendRequestDTOList = new ArrayList<FriendRequestDTO>();
        for(FriendRequest req : requets)
        {
            friendRequestDTOList.add(FriendRequestMapper.toFriendRequestDTO(req));
        }*/
        return friendRequestRepository.getAllUnacceptedRequestBySenderId(sender_id);
    }

    @Override
    public List<FriendRequest> getAllUnacceptedRequestToSenderId(String sender_id) {
        return friendRequestRepository.getAllUnacceptedRequestToSenderId(sender_id);
    }
    @Override
    public List<FriendRequest> getAllRequest() {
        return friendRequestRepository.findAll();
    }
    @Override
    public Boolean createFriendRequest(String sender_id, String recipient_id) {
        // check if friend request already exists
        int checkExist = friendRequestRepository.checkFriendRequestExist(sender_id, recipient_id);
        if (checkExist > 0)
        {
            throw new IllegalStateException("Friend Request already been created");
        }
        else {
            friendRequestRepository.addFriendRequest(sender_id,recipient_id);
            return true;
        }
    }

    @Override
    public FriendRequest deleteFriendRequest() {
        return null;
    }
}
