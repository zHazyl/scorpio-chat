package com.tnh.authservice.service.implement;

import com.tnh.authservice.domain.User;
import com.tnh.authservice.repository.UserRepository;
import com.tnh.authservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;
    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createNewUser (User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).get();
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
