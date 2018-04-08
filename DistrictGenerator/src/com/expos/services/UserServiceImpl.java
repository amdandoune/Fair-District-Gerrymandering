package com.expos.services;

import com.expos.dao.UserDao;
import com.expos.models.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Transactional
    public void save(UsersEntity user) {
        userDao.save(user);
    }

    @Transactional
    public UsersEntity login(String username, String password) {
        List<UsersEntity> list = userDao.list();
        for (UsersEntity user : list) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Transactional
    public boolean exists(String username) {
        List<UsersEntity> list = userDao.list();
        for (UsersEntity user : list) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

}
