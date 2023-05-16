package com.tnh.groupchatservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
public class GroupMemberDTO {

    private Long id;
    private GroupChatDTO group;
    private ChatProfileDTO member;
    private boolean isAdmin;

}
