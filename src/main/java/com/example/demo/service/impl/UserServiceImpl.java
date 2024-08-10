package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.pojo.UserPojo;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void saveData(UserPojo userPojo) {
        User user = new User();
        user.setEmail(userPojo.getEmail());
        user.setFullName(userPojo.getFullName());
        user.setPassword(userPojo.getPassword());

        userRepository.save(user);
    }

    @Override
    public String login(UserPojo userPojo) {
        User user = userRepository.findByEmail(userPojo.getEmail());
        if (user != null && user.getPassword().equals(userPojo.getPassword())) {
            return "Login successful";
        } else {
            return "Invalid credentials";
        }
    }

    @Override
    public void updateProfile(UserPojo userPojo) {
        User user = userRepository.findByEmail(userPojo.getEmail());
        if (user != null) {
            user.setFullName(userPojo.getFullName());
            userRepository.save(user);
        }
    }

    @Override
    public void changePassword(UserPojo userPojo) {
        User user = userRepository.findByEmail(userPojo.getEmail());
        if (user != null) {
            user.setPassword(userPojo.getPassword());
            userRepository.save(user);
        }
    }
}
