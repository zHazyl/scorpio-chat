package com.tnh.groupchatservice.mapper;

import com.tnh.groupchatservice.domain.GroupChat;
import com.tnh.groupchatservice.dto.GroupChatDTO;
import com.tnh.groupchatservice.utils.DateUtils;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupChatMapper {

    @Named("mapToGroupChatDTO")
    @Mapping(target = "createdDate", expression = "java(convertOffsetDateToString(groupChat.getCreatedDate()))")
    GroupChatDTO mapToGroupChatDTO(GroupChat groupChat);

    @IterableMapping(qualifiedByName = {"mapToGroupChatDTO"})
    List<GroupChatDTO> mapToGroupChatList(List<GroupChat> groupChats);

    default String convertOffsetDateToString(OffsetDateTime time) {
        return time.format(DateTimeFormatter.ofPattern(DateUtils.DATE_PATTERN));
    }
}
