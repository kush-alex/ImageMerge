package com.kushnarenko.controller;

import com.kushnarenko.model.Role;
import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;
import com.kushnarenko.service.ThingService;
import com.kushnarenko.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ThingService thingService;

    @RequestMapping(method = RequestMethod.GET)
    public Principal user(Principal principal) {
        String facebookId = principal.getName();
        User user = userService.findByFacebookId(facebookId);
        if (user == null) {
            user = new User();
            String[] split = ((OAuth2Authentication) principal).getUserAuthentication().getDetails().toString().split("((.)*name=)|(,(.)*)");
            user.setName(split[1]);
            user.setFacebookId(facebookId);
            user.setRole(Role.USER);
            userService.saveUser(user);
        }
        return principal;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByFacebookId/{facebookId}")
    public User user(@PathVariable String facebookId) {
        return userService.findByFacebookId(facebookId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{facebookId}/things")
    public Set<Thing> getUserThings(@PathVariable String facebookId) {
        return thingService.findAllUserThings(facebookId);
    }
}
