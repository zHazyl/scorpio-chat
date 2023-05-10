package com.tnh.authservice.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
