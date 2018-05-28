package com.expos.dao;

import com.expos.models.DistrictsEntity;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictDaoImpl implements DistrictDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<DistrictsEntity> list(String stateName, int year) {
        Session session = null;
        List<DistrictsEntity> list = null;
        try {
            session = sessionFactory.openSession();
            String sql = "SELECT * FROM districts WHERE stateName = (:stateName) AND year = (:year)";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(DistrictsEntity.class);
            query.setParameter("stateName", stateName);
            query.setParameter("year", year);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
