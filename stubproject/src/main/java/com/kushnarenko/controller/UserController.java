package com.kushnarenko.controller;

import com.kushnarenko.model.Thing;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Set;

/**
 * Created by Oleksandr_Kushnarenk on 5/26/2017.
 */
public interface UserController {
    @RequestMapping(method = RequestMethod.GET)
    Principal user(Principal principal);

    @RequestMapping(method = RequestMethod.GET, value = "/findByFacebookId/{facebookId}")
    com.kushnarenko.model.User user(@PathVariable String facebookId);

    @RequestMapping(method = RequestMethod.GET, value = "/{facebookId}/things")
    Set<Thing> getUserThings(@PathVariable String facebookId);

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    String uploadImage(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("thing") String thing);

    @RequestMapping(value = "/createRecord", method = RequestMethod.POST)
    @ResponseBody
    Thing createRecord(@RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2, @RequestParam("recordName") String recordName);
}
