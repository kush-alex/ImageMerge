package com.kushnarenko.dao;

import java.util.List;
import com.kushnarenko.model.User;

public interface UserDao {

    User findByUsername(String username);

    User findByFacebookId(String username);

    void saveUser(User user);

    List<User> findAllUsers();

    User updateUser(User user);

}