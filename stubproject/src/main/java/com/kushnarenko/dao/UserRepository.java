package com.kushnarenko.dao;

import com.kushnarenko.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.facebookId=:facebook_id")
    User findByFacebookId(@Param("facebook_id") String facebookId);

    @Query("SELECT u FROM User u WHERE u.name=:user_name")
    User findByUserName(@Param("user_name") String userName);

}