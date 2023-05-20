package com.tnh.authservice.service;

import com.tnh.authservice.domain.User;
import com.tnh.authservice.dto.UserDTO;
import org.springframework.amqp.core.Message;

public interface UserService {

    User createNewUser (User user);

    User getUserById (String id);
    User createUser(String id, String username, String password, String email, String firstName, String lastName);
    User modifyUser(String id, String firstName, String lastName);


//    boolean updateUserById (Long id, String password_hash, String first_name, String last_name, String email);
    void deleteUserById (String id);
    void voting(UserDTO userDTO);
}

