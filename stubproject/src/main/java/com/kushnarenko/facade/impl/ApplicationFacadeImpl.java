package com.kushnarenko.facade.impl;

import com.kushnarenko.constants.PathConstants;
import com.kushnarenko.facade.ApplicationFacade;
import com.kushnarenko.model.Role;
import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;
import com.kushnarenko.service.ImageService;
import com.kushnarenko.service.ThingService;
import com.kushnarenko.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.Set;

import static com.kushnarenko.constants.PathConstants.IMAGE_PATH;
import static com.kushnarenko.constants.PathConstants.IMAGE_TYPE;

@Component
public class ApplicationFacadeImpl implements ApplicationFacade {

    @Autowired
    ThingService thingService;

    @Autowired
    UserService userService;

    @Autowired
    ImageService imageService;

    @Override
    public Principal findOrCreateUser(Principal principal) {
        String facebookId = principal.getName();
        User user = userService.findByFacebookId(facebookId);
        if (user == null) {
            user = new User();
            String[] split = ((OAuth2Authentication) principal).getUserAuthentication().getDetails().toString().split("((.)*name=)|(,(.)*)");
            user.setName(split[1]);
            user.setFacebookId(facebookId);
            user.setRole(Role.USER);
            userService.saveUser(user);
        }
        return principal;
    }

    @Override
    public User findUser(String facebookId) {
        return userService.findByFacebookId(facebookId);
    }

    @Override
    public Set<Thing> getUserThings(String facebookId) {
        return thingService.findAllUserThings(facebookId);
    }


    @Override
    public String saveImage(MultipartFile file1, MultipartFile file2, String thingId) {
        String file1Name = IMAGE_PATH + thingId + "1" + IMAGE_TYPE;
        String file2Name = IMAGE_PATH + thingId + "2" + IMAGE_TYPE;
        imageService.saveImage(file1, file1Name);
        imageService.saveImage(file2, file2Name);
        return "Its Ok";
    }

    @Override
    @Transactional
    public Thing createRecord(MultipartFile file1, MultipartFile file2, String userFacebookId, String recordName) {
        Thing thing = new Thing();
        thing.setField(recordName);
        thing.setUser(userService.findByFacebookId(userFacebookId));
        thingService.saveThing(thing);
        String file1Name = thing.getId() + "1" + IMAGE_TYPE;
        String file2Name = thing.getId() + "2" + IMAGE_TYPE;
        imageService.saveImage(file1, file1Name);
        imageService.saveImage(file2, file2Name);
        thing.setImage1(file1Name);
        thing.setImage2(file2Name);
        thingService.updateThing(thing);
        return thing;
    }
}
