package com.tnh.friendchatservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatProfileDTO {
    private String userId;
    private String friendsRequestCode;
}
