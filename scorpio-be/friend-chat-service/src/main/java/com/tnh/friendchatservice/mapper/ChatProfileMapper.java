package com.tnh.friendchatservice.mapper;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.dto.ChatProfileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ChatProfileMapper {

    @Named("chatProfileToChatProfileDTO")
//    @Mapping(target = "userId", expression = "java(convertUUIDtoString(chatProfile.getUserId()))")
    ChatProfileDTO chatProfileToChatProfileDTO(ChatProfile chatProfile);

//    default String convertUUIDtoString(UUID id) {
//        return id.toString();
//    }

}
