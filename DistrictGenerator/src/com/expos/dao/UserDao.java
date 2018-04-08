package com.expos.dao;

import com.expos.models.UsersEntity;

import java.util.List;

public interface UserDao {

    void save(UsersEntity user);
    List list();

}
