package com.expos.dao;

import com.expos.models.UsersEntity;

import java.util.List;

public interface UserDao {

    void save(UsersEntity user);

    List list();

    void update(UsersEntity user);

    void delete(UsersEntity user);

    List<Integer> getVisisted();

}
