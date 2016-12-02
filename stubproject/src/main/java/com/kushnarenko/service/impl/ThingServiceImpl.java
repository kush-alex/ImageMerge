package com.kushnarenko.service.impl;

import com.kushnarenko.dao.ThingRepository;
import com.kushnarenko.dao.UserRepository;
import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;
import com.kushnarenko.service.ThingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("ThingService")
@Transactional
public class ThingServiceImpl implements ThingService {

//    @Autowired
//    private ThingDao thingDao;

    @Autowired
    private ThingRepository thingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void saveThing(Thing thing) {
        thingRepository.save(thing);
        //thingDao.saveThing(thing);
    }

    @Override
    public void updateThing(Thing thing) {
        thingRepository.save(thing);
//        thingDao.saveThing(thing);
    }

    @Override
    public Thing findById(String id) {
        long longId = Long.parseLong(id);
        return thingRepository.findOne(longId);
//        return thingDao.findById(longId);
    }

    @Override
    public Set<Thing> findAllUserThings(String facebookId) {
        User user = userRepository.findByFacebookId(facebookId);
        return thingRepository.findAllByUserId(user.getId());
    }
}
