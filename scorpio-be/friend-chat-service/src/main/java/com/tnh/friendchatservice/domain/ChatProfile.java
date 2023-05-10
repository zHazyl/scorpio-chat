package com.tnh.friendchatservice.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Table(name = "Chat_Profile")
@Entity
@NoArgsConstructor
public class ChatProfile {

    @Id
    @Column(nullable = false, name = "user_id", unique = true, columnDefinition = "BINARY(16)")
    private String userId;

    @Column(name = "friends_request_code", nullable = false, length = 64, unique = true)
    private String friendsRequestCode;

}
