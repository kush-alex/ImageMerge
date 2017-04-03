package com.kushnarenko.facade.impl;

import com.kushnarenko.facade.ApplicationFacade;
import com.kushnarenko.model.Role;
import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;
import com.kushnarenko.service.ImageService;
import com.kushnarenko.service.ThingService;
import com.kushnarenko.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public String saveImage(List<MultipartFile> files, String thingId) {
        for (int i = 0; i < files.size(); i++) {
            String fileName = IMAGE_PATH + thingId + i + IMAGE_TYPE;
            imageService.saveImage(files.get(i), fileName);
        }
        return "Its Ok";
    }

    @Override
    @Transactional
    public Thing createRecord(List<MultipartFile> files, String recordName) {
        Thing thing = Thing.builder().field(recordName).user(getCurrentUser()).build();
        thingService.saveThing(thing);

        HashMap<String, MultipartFile> fileHashMap = new HashMap<>();
        files.forEach(file -> fileHashMap.put(thing.getId() + files.indexOf(file) + IMAGE_TYPE, file));
        fileHashMap.keySet().forEach(fileName -> imageService.saveImage(fileHashMap.get(fileName), fileName));
        thing.setImages(new ArrayList<>(fileHashMap.keySet()));

        thingService.updateThing(thing);

        return thing;
    }

    private User getCurrentUser() {
        String facebookId = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByFacebookId(facebookId);
    }

    @Override
    public MultipartFile getFusedImage(String itemId) {
        Thing thing = thingService.findById(itemId);

        try {
            ProcessBuilder builder = new ProcessBuilder("Rscript", "D:/temp/StubSpringProject/stubproject/rScript/script.R", thing.getImage1(), thing.getImage2());
            builder.redirectErrorStream(true);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
