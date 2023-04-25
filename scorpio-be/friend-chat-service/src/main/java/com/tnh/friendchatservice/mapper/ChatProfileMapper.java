package com.tnh.friendchatservice.mapper;

import com.tnh.friendchatservice.domain.ChatProfile;
import com.tnh.friendchatservice.dto.ChatProfileDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel  = "spring" )
public interface ChatProfileMapper {
    @Named("mapChatProfileToChatProfileDTO")
    @Mapping(target = "useId", expression = "java(convertUUIDToString(ChatProfile.getUserId()))")
    ChatProfileDTO mapChatProfileToChatProfileDTO(ChatProfile chatProfile);

    default String convertUUIDToString(UUID id ){ return id.toString();}
}
