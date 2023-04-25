package com.tnh.friendchatservice.domain;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@NoArgsConstructor
//@Table(name = "friend_chat")
//public class FriendChat {
//
//    @Id
//    private Long id;
//    private Long chat_with_id;
//    private String sender_id;
//    private String recipient_id;
//    private String s_nickname;
//    private String r_nickname;
//}
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table( name = "friend_chat",
        uniqueConstraints = @UniqueConstraint(columnNames = {"chat_with_id", "sender_id", "recipient_id"}))
@Entity
public class FriendChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
    @JoinColumn(name = "chat_with_id")
    private FriendChat chatwith;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private ChatProfile sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private ChatProfile recipient;


    @JoinColumn(name = "s_nickname", nullable = true)
    private String s_nickname ;

    @JoinColumn(name = "r_nickname", nullable = true)
    private String r_nickname ;

}
