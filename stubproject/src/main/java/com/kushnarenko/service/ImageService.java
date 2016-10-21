package com.kushnarenko.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String saveImage(MultipartFile multipartFile, String name);

}