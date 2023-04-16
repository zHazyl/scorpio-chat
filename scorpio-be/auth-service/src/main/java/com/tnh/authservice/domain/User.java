package com.tnh.authservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "userau")
public class User {
    @Id
    private String id;
    private String username;
    private String password_hash;
    private String first_name;
    private String last_name;
    private String email;
}
