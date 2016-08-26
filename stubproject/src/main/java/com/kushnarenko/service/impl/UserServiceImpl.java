package com.kushnarenko.service.impl;

import java.util.List;

import com.kushnarenko.dao.UserDao;
import com.kushnarenko.model.User;
import com.kushnarenko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public List<User> findAllUser() {
        return userDao.findAllUsers();
    }

}
