package com.expos.dao;

import com.expos.models.StatesEntity;
import com.expos.models.StatesEntityPK;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateDaoImpl implements StateDao {

    @Autowired
    private SessionFactory sessionFactory;

    public StatesEntity get(StatesEntityPK stateKey) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session.get(StatesEntity.class, stateKey);
    }


    public List get2(int year) {
        Session session = null;
        Query query = null;
        try {
            session = sessionFactory.openSession();
            query = session.createQuery("select s from StatesEntity s where s.year = :year");
            query.setParameter("year", year);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return query.list();
    }


}
