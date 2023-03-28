package com.tnh.friendchatservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_profile")
public class ChatProfile {

    @Id
    @Column(name = "user_id", nullable = false, columnDefinition = "VARCHAR(64)")
    private String user_id;
    @Column(name = "friends_request_code", nullable = false, unique = true, columnDefinition = "VARCHAR(64)")
    private String friends_request_code;
}
