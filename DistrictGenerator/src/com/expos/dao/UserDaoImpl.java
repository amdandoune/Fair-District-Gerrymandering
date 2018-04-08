package com.expos.dao;

import com.expos.models.UsersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(UsersEntity user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(user);
            System.out.println("Identifier");
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<UsersEntity> list() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session.createQuery("from UsersEntity ").getResultList();
    }


}
