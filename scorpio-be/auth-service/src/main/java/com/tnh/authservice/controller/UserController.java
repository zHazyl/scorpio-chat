package com.tnh.authservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @PostMapping("/authenticate")
    public void login(@RequestBody String str) {
        log.debug("oke");
    }


}
