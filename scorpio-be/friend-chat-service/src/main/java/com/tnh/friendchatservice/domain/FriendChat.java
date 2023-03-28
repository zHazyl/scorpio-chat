package com.tnh.friendchatservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "friendchat")
public class FriendChat {

    @Id
    private Long id;
    private Long chat_with_id;
    private String sender_id;
    private String recipient_id;
    private String nickname;
}
