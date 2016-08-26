package com.kushnarenko.service.impl;

import com.kushnarenko.dao.EntityDao;
import com.kushnarenko.model.Entity;
import com.kushnarenko.model.User;
import com.kushnarenko.service.EntityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service("entityService")
@Transactional
public class EntityServiceImpl implements EntityService {

    @Autowired
    private EntityDao entityDao;

    @Override
    public void saveEntity(Entity entity) {
        entityDao.saveEntity(entity);
    }

}
