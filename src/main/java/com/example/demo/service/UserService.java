package com.example.demo.service;

import com.example.demo.pojo.UserPojo;

public interface UserService {
    void saveData(UserPojo userPojo);
    String login(UserPojo userPojo);
}
