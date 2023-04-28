package com.tnh.friendchatservice.mapper;


import com.tnh.friendchatservice.domain.FriendChat;
import com.tnh.friendchatservice.dto.FriendChatDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FriendChatMapper {

    @Named("mapFriendChatToFriendChatDTO")
    @Mapping(target = "chatWith", expression = "java(convertChatWithToId(friendChat.getChatWith()))")
//  @Mapping(target = "recipient", expression = "java(convertRecipientToUserId(friendChat.getRecipient()))")
    FriendChatDTO mapToFriendChatDTO(FriendChat friendChat);

    @IterableMapping(qualifiedByName = {"mapFriendChatToFriendChatDTO"})
    List<FriendChatDTO> mapToFriendChatList(List<FriendChat> friendChats);

    default Long convertChatWithToId(FriendChat chatWith) {
        return chatWith.getId();
    }
//    default String convertRecipientToUserId(ChatProfile recipient) {
//        return recipient.getUserId();
//    }

}
