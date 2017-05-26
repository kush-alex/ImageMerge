package com.kushnarenko.controller;

import com.kushnarenko.facade.ApplicationFacade;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

import javax.servlet.ServletContext;

@RestController
@RequestMapping("image")
public class ImageControllerImpl implements ImageController {

    @Autowired
    ApplicationFacade applicationFacade;

    @Autowired
    private ServletContext context;

    @Override
    public byte[] getFusedUmage(@RequestParam("item") String item) throws IOException {
        String fusedImage = applicationFacade.getFusedImage(item);
        InputStream stream = new BufferedInputStream(new FileInputStream(fusedImage));
        return IOUtils.toByteArray(stream);
    }

}
