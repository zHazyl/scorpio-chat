package com.tnh.friendchatservice.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "friend_request",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"sender_chat_profile_id","recipient_chat_profile_id"})})
public class FriendRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "sender_chat_profile_id", nullable = false, columnDefinition = "VARCHAR(64)")
    private String sender_id;
    @Column(name = "recipient_chat_profile_id", nullable = false, columnDefinition = "VARCHAR(64)" )
    private String recipient_id;
    @Column(name = "sent_time", nullable = false, columnDefinition = "DATETIME")
    private Date sent_time;
    @Column(name = "is_accepted", nullable = false, columnDefinition = "BIT DEFAULT 0")
    private Boolean is_accepted;
}
