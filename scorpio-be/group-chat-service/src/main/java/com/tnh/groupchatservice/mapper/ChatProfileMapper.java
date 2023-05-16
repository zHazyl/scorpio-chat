package com.tnh.groupchatservice.mapper;

import com.tnh.groupchatservice.domain.ChatProfile;
import com.tnh.groupchatservice.dto.ChatProfileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ChatProfileMapper {

    @Named("chatProfileToChatProfileDTO")
//    @Mapping(target = "userId", expression = "java(convertUUIDtoString(chatProfile.getUserId()))")
    ChatProfileDTO chatProfileToChatProfileDTO(ChatProfile chatProfile);

//    default String convertUUIDtoString(UUID id) {
//        return id.toString();
//    }

}
