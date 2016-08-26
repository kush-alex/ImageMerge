package com.kushnarenko.dao;

import com.kushnarenko.model.Entity;
import com.kushnarenko.model.User;

import java.util.Set;

public interface EntityDao {

    void saveEntity(Entity entity);

    Set<Entity> findAllUserEntitys(User user);

}