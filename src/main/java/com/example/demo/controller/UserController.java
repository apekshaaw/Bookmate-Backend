package com.example.demo.controller;

import com.example.demo.pojo.UserPojo;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/User")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody UserPojo userPojo) {
        try {
            userService.saveData(userPojo);
            return new ResponseEntity<>("Signup successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Signup failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserPojo userPojo) {
        String response = userService.login(userPojo);
        if ("Login successful".equals(response)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
