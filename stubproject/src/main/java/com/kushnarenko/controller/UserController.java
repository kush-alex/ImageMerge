package com.kushnarenko.controller;

import com.kushnarenko.facade.ApplicationFacade;
import com.kushnarenko.model.Role;
import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;
import com.kushnarenko.service.ImageService;
import com.kushnarenko.service.ThingService;
import com.kushnarenko.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    ApplicationFacade applicationFacade;

    @RequestMapping(method = RequestMethod.GET)
    public Principal user(Principal principal) {
        return applicationFacade.findOrCreateUser(principal);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByFacebookId/{facebookId}")
    public User user(@PathVariable String facebookId) {
        return applicationFacade.findUser(facebookId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{facebookId}/things")
    public Set<Thing> getUserThings(@PathVariable String facebookId) {
        return applicationFacade.getUserThings(facebookId);
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    public
    @ResponseBody
    String uploadImage(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("thing") String thing) {
        return applicationFacade.saveImage(file1, file2, thing);
    }

}
