package com.kushnarenko.controller;

import com.kushnarenko.facade.ApplicationFacade;
import com.kushnarenko.model.Thing;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("image")
public class ImageController {

    @Autowired
    ApplicationFacade applicationFacade;

    @RequestMapping(method = RequestMethod.GET)
    public MultipartFile getFusedUmage(@RequestParam("item") String item) {
        return applicationFacade.getFusedImage(item);
    }

}
