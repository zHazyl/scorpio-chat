package com.tnh.friendchatservice.service.impl;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.domain.FriendChat;
import com.tnh.friendchatservice.repository.ChatProfileRepository;
import com.tnh.friendchatservice.repository.FriendChatRepository;
import com.tnh.friendchatservice.service.FriendChatService;
import com.tnh.friendchatservice.utils.exception.AlreadyExistException;
import com.tnh.friendchatservice.utils.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FriendChatServiceImpl implements FriendChatService {

    // Dependency injection - should read this
    private final FriendChatRepository friendChatRepository;
    private final ChatProfileRepository chatProfileRepository;

    public FriendChatServiceImpl(ChatProfileRepository chatProfileRepository, FriendChatRepository friendChatRepository) {
        this.chatProfileRepository = chatProfileRepository;
        this.friendChatRepository = friendChatRepository;
    }

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

        friendChatForFirstUser.setChatwith(friendChatForSecondUser);
        friendChatForSecondUser.setChatwith(friendChatForFirstUser);


        friendChatRepository.save(friendChatForFirstUser);
        friendChatRepository.save(friendChatForSecondUser);


    }

    @Override
    public List<FriendChat> getAllFriendsChatsBySender(String currentUserId) {

        List<FriendChat> friendChats = null;

//        friendChats = chatProfileRepository.findById(UUID.fromString(currentUserId))
//                .map(friendChatRepository::findBySender)
//                .orElseThrow(() -> new NotFoundException(
//                        "User with id " + currentUserId + " not found"));
//
//        });
        return friendChats;
    }

    @Transactional
    @Override
    public void deleteFriendChat(long friendChatId, long friendChatWithId, String currentUserId) {
        var friendChat =
                friendChatRepository.findByIdAndFriendChatWithIdAndSenderId(
                                friendChatId, friendChatWithId,
                                UUID.fromString(currentUserId))
                        .orElseThrow(() -> new NotFoundException("Friend chat not found"));
//        friendRequestRepository.deleteFriendRequestByChatProfiles(friendChat.getSender(),
//                friendChat.getRecipient());
//        friendChatRedisRepository.deleteFriendChat(
//                friendChat.getSender().getUserId().toString(),
//                Long.toString(friendChatId)
//        );

        friendChatRepository.delete(friendChat);
    }
}
