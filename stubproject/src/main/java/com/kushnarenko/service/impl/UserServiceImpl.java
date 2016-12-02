package com.kushnarenko.service.impl;

import java.util.List;

import com.kushnarenko.dao.UserRepository;
import com.kushnarenko.model.User;
import com.kushnarenko.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByFacebookId(String facebookId) {
        return userRepository.findByFacebookId(facebookId);
    }

    @Override
    public List<User> findAllUser() {
        return findAllUser();
    }

}
