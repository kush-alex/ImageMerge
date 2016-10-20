package com.kushnarenko.service;

import com.kushnarenko.model.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    void saveUser(User user);

    User findByFacebookId(String facebookId);

    List<User> findAllUser();

}