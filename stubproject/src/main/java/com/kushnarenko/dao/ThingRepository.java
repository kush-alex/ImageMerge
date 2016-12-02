package com.kushnarenko.dao;

import com.kushnarenko.model.Thing;
import com.kushnarenko.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ThingRepository extends CrudRepository<Thing, Long> {

    @Query("FROM Thing t WHERE t.user=:user_id")
    Set<Thing> findAllByUserId(@Param("user_id") Long userId);

}