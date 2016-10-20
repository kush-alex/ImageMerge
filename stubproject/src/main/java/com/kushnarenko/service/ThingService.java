package com.kushnarenko.service;

import com.kushnarenko.model.Thing;

import java.util.List;
import java.util.Set;

public interface ThingService {

    void saveThing(Thing Thing);

    Set<Thing> findAllUserThings(String facebookId);

}