package com.tnh.authservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("UserRedis")
@Getter
@Setter
@NoArgsConstructor
public class UserRedis implements Serializable {

    @Id
    private String id;
    private String username;
    private String email;
    private String firstName;

    public UserRedis(String id, String username, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String lastName;

}
