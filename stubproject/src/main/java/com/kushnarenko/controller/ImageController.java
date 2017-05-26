package com.kushnarenko.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

/**
 * Created by Oleksandr_Kushnarenk on 5/26/2017.
 */
public interface ImageController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    byte[] getFusedUmage(@RequestParam("item") String item) throws IOException;

}
