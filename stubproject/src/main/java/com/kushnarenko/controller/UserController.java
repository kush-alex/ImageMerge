package com.kushnarenko.controller;

import com.kushnarenko.model.User;
import com.kushnarenko.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/user")
    public Principal user(Principal principal) {

        System.out.println(principal.getName());

        
        User user = new User();
        user.setName(principal.getName());
        return principal;
    }
}
