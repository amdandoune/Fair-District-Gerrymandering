package com.expos.services;


import com.expos.models.UsersEntity;

import java.util.List;

public interface UserService {

    List<UsersEntity> getUserList();

    void save(UsersEntity user);

    UsersEntity login(String username, String password);

    boolean exists(String username);

    UsersEntity getUser(String username);

    void update(UsersEntity user);

    void delete(UsersEntity user);

    String encrypt(String password);

    List<Integer> getVisited();

    String getProperties();

    void writeProperties(String updatedProperties);

}
