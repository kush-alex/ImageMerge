package com.kushnarenko.facade;

import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;

import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Set;

public interface ApplicationFacade {

    String saveImage(MultipartFile file1, MultipartFile file2, String thingId);

    Principal findOrCreateUser(Principal principal);

    User findUser(String facebookId);

    Set<Thing> getUserThings(String facebookId);

}
