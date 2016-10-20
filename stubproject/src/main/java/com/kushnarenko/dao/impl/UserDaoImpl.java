package com.kushnarenko.dao.impl;

import com.kushnarenko.dao.AbstractDao;
import com.kushnarenko.dao.UserDao;
import com.kushnarenko.model.User;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    @Override
    public User findByUsername(String username) {
        List<User> users = getSession().createCriteria(User.class).add(Restrictions.eq("username", username)).list();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User findByFacebookId(String facebookId) {
        List<User> users = getSession().createCriteria(User.class).setFetchMode("user", FetchMode.JOIN).add(Restrictions.eq("facebookId", facebookId)).list();
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void saveUser(User user) {
        persist(user);
    }

    @Override
    public List<User> findAllUsers() {
        Criteria criteria = createThingCriteria();
        return (List<User>) criteria.list();
    }

    @Override
    public User updateUser(User user) {
        getSession().saveOrUpdate(user);
        return user;
    }
}
