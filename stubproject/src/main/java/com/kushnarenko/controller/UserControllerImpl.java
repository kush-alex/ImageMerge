package com.kushnarenko.controller;

import com.kushnarenko.facade.ApplicationFacade;
import com.kushnarenko.model.Thing;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Arrays;
import java.util.Set;

@RestController
@RequestMapping("user")
public class UserControllerImpl implements UserController {

    @Autowired
    private ApplicationFacade applicationFacade;

    @Override
    public Principal user(Principal principal) {
        return applicationFacade.findOrCreateUser(principal);
    }

    @Override
    public com.kushnarenko.model.User user(@PathVariable String facebookId) {
        return applicationFacade.findUser(facebookId);
    }

    @Override
    public Set<Thing> getUserThings(@PathVariable String facebookId) {
        return applicationFacade.getUserThings(facebookId);
    }

    @Override
    public
    @ResponseBody
    String uploadImage(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("thing") String thing) {
        return applicationFacade.saveImage(Arrays.asList(file1, file2), thing);
    }

    @Override
    public
    @ResponseBody
    Thing createRecord(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("recordName") String recordName) {
        return applicationFacade.createRecord(Arrays.asList(file1, file2), recordName);
    }

}
