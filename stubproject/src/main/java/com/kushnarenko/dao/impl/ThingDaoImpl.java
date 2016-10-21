package com.kushnarenko.dao.impl;

import com.kushnarenko.dao.AbstractDao;
import com.kushnarenko.dao.ThingDao;
import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;

import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository("thingDao")
public class ThingDaoImpl extends AbstractDao<Long, Thing> implements ThingDao {


    @Override
    public void saveThing(Thing thing) {
        getSession().save(thing);

    }

    @Override
    public Set<Thing> findAllUserThings(String facebookId) {
        List<User> users = getSession().createCriteria(User.class).add(Restrictions.eq("facebookId", facebookId)).list();
        User user1 = users.isEmpty() ? null : users.get(0);
        return user1.getThings();
    }
}
