package com.kushnarenko.facade;

import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;

import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface ApplicationFacade {

    String saveImage(List<MultipartFile> files, String thingId);

    Thing createRecord(List<MultipartFile> files, String recordName);

    Principal findOrCreateUser(Principal principal);

    User findUser(String facebookId);

    Set<Thing> getUserThings(String facebookId);

    MultipartFile getFusedImage(String itemId);
}
