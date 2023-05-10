package com.tnh.friendchatservice.service.impl;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.domain.FriendChat;
import com.tnh.friendchatservice.domain.FriendChatRedis;
import com.tnh.friendchatservice.repository.ChatProfileRepository;
import com.tnh.friendchatservice.repository.FriendChatRedisRepository;
import com.tnh.friendchatservice.repository.FriendChatRepository;
import com.tnh.friendchatservice.repository.FriendRequestRepository;
import com.tnh.friendchatservice.service.FriendChatService;
import com.tnh.friendchatservice.utils.exception.AlreadyExistException;
import com.tnh.friendchatservice.utils.exception.NotFoundException;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendChatServiceImpl implements FriendChatService {

    // Dependency injection - should read this
    private final FriendChatRepository friendChatRepository;
    private final ChatProfileRepository chatProfileRepository;
    private final FriendChatRedisRepository friendChatRedisRepository;
    private final FriendRequestRepository friendRequestRepository;


    @Transactional
    @Override
    public void addFriendChat(ChatProfile firstUserChatProfile, ChatProfile secondUserChatProfile) {

        if (friendChatRepository.existsFriendChatForUsers(firstUserChatProfile, secondUserChatProfile)) {
            throw new AlreadyExistException("Chat for users already exists");
        }

        var friendChatForFirstUser = new FriendChat();
        var friendChatForSecondUser = new FriendChat();

        friendChatForFirstUser.setSender(firstUserChatProfile);
        friendChatForFirstUser.setRecipient(secondUserChatProfile);

        friendChatForSecondUser.setSender(secondUserChatProfile);
        friendChatForSecondUser.setRecipient(firstUserChatProfile);

        friendChatRepository.save(friendChatForFirstUser);
        friendChatRepository.save(friendChatForSecondUser);

        friendChatForFirstUser.setChatWith(friendChatForSecondUser);
        friendChatForSecondUser.setChatWith(friendChatForFirstUser);

        friendChatRepository.save(friendChatForFirstUser);
        friendChatRedisRepository.save(friendChatForFirstUser);
        friendChatRepository.save(friendChatForSecondUser);
        friendChatRedisRepository.save(friendChatForSecondUser);

    }

    @Override
    public List<FriendChatRedis> getAllFriendChatsRedisBySender(
            String currentUserId) {
        return friendChatRedisRepository
                .findAllBySender(currentUserId);
    }

    @Override
    public List<FriendChat> getAllFriendsChatsBySender(String currentUserId) {

        List<FriendChat> friendChats ;
        var sender = chatProfileRepository.findById(currentUserId)
                .orElseThrow(() -> new NotFoundException(
                        "User with id " + currentUserId + " not found"
                )
        );

        friendChats = friendChatRepository.findBySender(sender);
        if (friendChats != null) {
            friendChats.forEach(friendChatRedisRepository::save);
        }

        return friendChats;

    }

    @Transactional
    @Override
    public void deleteFriendChat(long friendChatId, long friendChatWithId, String currentUserId) {
        var friendChat =
                friendChatRepository.findByIdAndFriendChatWithIdAndSenderId(
                                friendChatId, friendChatWithId,
                                currentUserId)
                        .orElseThrow(() -> new NotFoundException("Friend chat not found"));
//        friendRequestRepository.changeStatusFriendRequestByChatProfiles(friendChat.getSender(),
//                friendChat.getRecipient());
//        );

        friendRequestRepository.deleteFriendRequestByChatProfiles(friendChat.getSender(),
                friendChat.getRecipient());
        friendChatRedisRepository.deleteFriendChat(
                friendChat.getSender().getUserId(),
                Long.toString(friendChatId)
        );
        friendChatRedisRepository.deleteFriendChat(
                friendChat.getRecipient().getUserId(),
                Long.toString(friendChatWithId)
        );

        friendChatRepository.delete(friendChat);
    }
}
