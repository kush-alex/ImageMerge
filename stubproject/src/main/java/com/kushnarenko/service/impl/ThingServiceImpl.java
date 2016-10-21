package com.kushnarenko.service.impl;

import com.kushnarenko.dao.ThingDao;
import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;
import com.kushnarenko.service.ThingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service("ThingService")
@Transactional
public class ThingServiceImpl implements ThingService {

    @Autowired
    private ThingDao thingDao;

    @Override
    public void saveThing(Thing thing) {
        thingDao.saveThing(thing);
    }

    @Override
    public void updateThing(Thing thing) {
        thingDao.saveThing(thing);
    }

    @Override
    public Set<Thing> findAllUserThings(String facebookId) {
        return thingDao.findAllUserThings(facebookId);
    }
}
