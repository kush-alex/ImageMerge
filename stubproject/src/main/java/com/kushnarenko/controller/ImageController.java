package com.kushnarenko.controller;

import com.kushnarenko.facade.ApplicationFacade;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    ApplicationFacade applicationFacade;

    @Autowired
    private ServletContext context;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getFusedUmage(@RequestParam("item") String item) throws IOException {
        String fusedImage = applicationFacade.getFusedImage(item);
        InputStream stream = new BufferedInputStream(new FileInputStream(fusedImage));
        return IOUtils.toByteArray(stream);
    }

}
