package com.kushnarenko.dao;

import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;

import java.util.List;
import java.util.Set;

public interface ThingDao {

    void saveThing(Thing Thing);

    Set<Thing> findAllUserThings(String facebookId);

}