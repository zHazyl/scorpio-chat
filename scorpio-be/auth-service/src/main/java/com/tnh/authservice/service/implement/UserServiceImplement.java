package com.tnh.authservice.service.implement;

import com.tnh.authservice.config.KeycloakProvider;
import com.tnh.authservice.constant.ApplicationConstants;
import com.tnh.authservice.domain.User;
import com.tnh.authservice.repository.UserRedisRepository;
import com.tnh.authservice.repository.UserRepository;
import com.tnh.authservice.service.KeycloakAdminClientService;
import com.tnh.authservice.service.UserService;
import com.tnh.authservice.utils.exception.AlreadyExistsException;
import com.tnh.authservice.utils.exception.InvalidDataException;
import com.tnh.authservice.utils.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;
    private final UserRedisRepository userRedisRepository;
    private final PasswordEncoder passwordEncoder;
    private final KeycloakProvider keycloakProvider;
    private final KeycloakAdminClientService keycloakAdminClientService;
    @Override
    public User createNewUser (User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {

        User user = null;
        try {
            user = userRedisRepository.findUserById(id);
        } catch (Exception e) {

        }
        if (user != null) {
            return user;
        }

        user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Not found user with id " + id));
        userRedisRepository.save(user);
        return user;
    }

    @Override
    public User createUser(String id, String username, String password, String email, String firstName, String lastName) {

        if (isEmpty(username) || isBlank(username) || !StringUtils.isAlphanumeric(username)) {
            throw new InvalidDataException("Invalid username");
        }
        if (isEmpty(password) || isBlank(password)) {
            throw new InvalidDataException("Invalid password");
        }
        if (password.length() < ApplicationConstants.USER_PASSWORD_MIN_LENGTH
                || password.length() > ApplicationConstants.USER_PASSWORD_MAX_LENGTH) {
            throw new InvalidDataException("Invalid password length");
        }
        if (!new EmailValidator().isValid(email, null)) {
            throw new InvalidDataException("Invalid email");
        }
        if (isEmpty(firstName) || isBlank(firstName)) {
            throw new InvalidDataException("Invalid first name");
        }
        if (isEmpty(lastName) || isBlank(lastName)) {
            throw new InvalidDataException("Invalid last name");
        }
        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new AlreadyExistsException("User with username " + username + " already exists.");
        }
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new AlreadyExistsException("User with email " + email + " already exists.");
        }

        var user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword_hash(passwordEncoder.encode(password));
        user.setFirst_name(StringUtils.capitalize(firstName.toLowerCase()));
        user.setLast_name(StringUtils.capitalize(lastName.toLowerCase()));
        user.setEmail(email);

        return userRepository.save(user);
    }

//    @Override
//    public boolean updateUserById(Long id, String password_hash, String first_name, String last_name, String email) {
//        User user = userRepository.findById(id)
//                .orElseThrow(()-> new IllegalStateException(
//                        "User id " + id + " not found"
//                ));
//        if (password_hash!= null && password_hash.length()>0 && !Objects.equals(user.getPassword_hash(), password_hash)){
//            user.
//        }
//    }

    @Override
    public void deleteUserById(String id) {
            userRepository.deleteById(id);
    }
}
