package com.tnh.friendchatservice.dto;

import com.tnh.friendchatservice.domain.ChatProfile;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FriendChatDTO {
    private Long id;
    private Long chatWith;
    private ChatProfile recipient;
}
