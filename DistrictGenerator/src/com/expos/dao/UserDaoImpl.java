package com.expos.dao;

import com.expos.models.StatesEntity;
import com.expos.models.UsersEntity;
import org.hibernate.SQLQuery;
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

    public void update(UsersEntity user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(UsersEntity user) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getVisisted() {
        Session session = null;
        List<Integer> visitedList = null;
        try {
            session = sessionFactory.openSession();
            String sql = "select visited from states;";
            SQLQuery query = session.createSQLQuery(sql);
            visitedList = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return visitedList;
    }

}
