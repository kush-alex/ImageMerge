package com.kushnarenko.dao.impl;

import com.kushnarenko.dao.AbstractDao;
import com.kushnarenko.dao.EntityDao;
import com.kushnarenko.model.Entity;
import com.kushnarenko.model.User;

import org.hibernate.criterion.Expression;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("entityDao")
public class EntityDaoImpl extends AbstractDao<Long, Entity> implements EntityDao {


    @Override
    public void saveEntity(Entity entity) {
        persist(entity);
    }

    @Override
    public Set<Entity> findAllUserEntitys(User user) {
        List<User> users = getSession().createCriteria(User.class).add(Expression.eq("username", user.getUsername())).list();
        User user1 = users.isEmpty() ? null : users.get(0);
        return user1.getUserEntity();
    }
}
