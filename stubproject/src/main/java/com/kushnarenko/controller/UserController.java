package com.kushnarenko.controller;

import com.kushnarenko.facade.ApplicationFacade;
import com.kushnarenko.model.Thing;

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
    public com.kushnarenko.model.User user(@PathVariable String facebookId) {
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

    @RequestMapping(value = "/createRecord", method = RequestMethod.POST)
    public
    @ResponseBody
    Thing createRecord(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("recordName") String recordName) {
        return applicationFacade.createRecord(file1, file2, recordName);
    }

}
