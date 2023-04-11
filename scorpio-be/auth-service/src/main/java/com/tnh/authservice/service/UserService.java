package com.tnh.authservice.service;

import com.tnh.authservice.domain.User;

public interface UserService {

    User createNewUser (User user);

    User getUserById (String id);

//    boolean updateUserById (Long id, String password_hash, String first_name, String last_name, String email);
    void deleteUserById (String id);
}
