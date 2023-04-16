package com.tnh.authservice.controller;

import com.tnh.authservice.domain.User;
import com.tnh.authservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public User createNewUser (@RequestBody User user){
        return userService.createNewUser(user);
    }
    @GetMapping (path = "/{id}")
    public User getUserById(@PathVariable(value = "id") String id){
        return new User();//userService.getUserById(id);
    }
//    @PutMapping(path = "/{id}")
//    public boolean updateUserById(@PathVariable("id") Long id,
//                                  @RequestParam(required = false) String password_hash,
//                                  @RequestParam(required = false) String first_name,
//                                  @RequestParam(required = false)String last_name,
//                                  @RequestParam(required = false)String email){
//        return userService.updateUserById(id, password_hash, first_name, last_name, email);
//    }

    @DeleteMapping (path = "/{id}")
    public void deleteUserById(@PathVariable("id") String id){
        userService.deleteUserById(id);
    }
}
