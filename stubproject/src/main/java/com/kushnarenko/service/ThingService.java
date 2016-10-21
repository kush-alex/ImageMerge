package com.kushnarenko.service;

import com.kushnarenko.model.Thing;

import java.util.List;
import java.util.Set;

public interface ThingService {

    void saveThing(Thing thing);

    void updateThing(Thing thing);

    Set<Thing> findAllUserThings(String facebookId);

}