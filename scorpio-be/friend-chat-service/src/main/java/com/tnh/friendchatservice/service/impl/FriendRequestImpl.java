package com.tnh.friendchatservice.service.impl;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.domain.FriendRequest;
import com.tnh.friendchatservice.repository.ChatProfileRepository;
import com.tnh.friendchatservice.repository.FriendRequestRepository;
import com.tnh.friendchatservice.service.FriendRequestService;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendRequestImpl implements FriendRequestService{

    private FriendRequestRepository friendRequestRepository;
    private ChatProfileRepository chatProfileRepository;
    @Autowired
    public  FriendRequestImpl (FriendRequestRepository freq, ChatProfileRepository chat){
        this.friendRequestRepository = freq;
        this.chatProfileRepository = chat;
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
    public FriendRequest createFriendRequest(String sender_id, String friend_request_code) {

        ChatProfile checkSenderProfile =
                chatProfileRepository.findById(sender_id).orElseThrow(() -> new RuntimeException("Sender not exist"));

        ChatProfile checkRecipientProfile =
                chatProfileRepository.findById(friend_request_code).orElseThrow(() -> new RuntimeException("Friend Request Code Not Found"));

        //Nếu gửi lời mời kết bạn cho chính mình thì sẽ bị lỗi
        if (checkSenderProfile.getFriends_request_code()==friend_request_code)
        {
            throw new IllegalArgumentException("Can not send request to yourself");
        }
        //Nếu request đã tồn tại
        if (friendRequestRepository.checkFriendRequestExist(sender_id, checkRecipientProfile.getUser_id())>0){

            throw new EntityExistsException("Friend Request already exist");
        }
        FriendRequest newFriendRequest = friendRequestRepository.addFriendRequest(sender_id, checkRecipientProfile.getUser_id());
        return newFriendRequest;
    }

    @Override
    public void deleteFriendRequest(String sender_id, long friendRequestId) {

    }


    @Override
    public FriendRequest deleteFriendRequest() {
        return null;
    }
}
