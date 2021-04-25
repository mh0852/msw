package com.mh.msw.service;

import com.mh.msw.model.User;

import java.util.List;

public interface UserService {
    List<User> getUserList();
    Integer addUser(User user);
    Integer updateUser(User user);
    User findUserByUsername(User user);
}
