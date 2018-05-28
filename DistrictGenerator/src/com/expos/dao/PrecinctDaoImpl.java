package com.expos.dao;

import com.expos.models.PrecinctsEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecinctDaoImpl implements PrecinctDao {

    @Autowired
    private SessionFactory sessionFactory;

    public List<PrecinctsEntity> list(String stateName, int year, String districtName) {
        Session session = null;
        List<PrecinctsEntity> list = null;
        try {
            session = sessionFactory.openSession();
            String sql = "SELECT * FROM precincts WHERE stateName = (:stateName) AND year = (:year) AND districtName = (:districtName)";
            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(PrecinctsEntity.class);
            query.setParameter("stateName", stateName);
            query.setParameter("year", year);
            query.setParameter("districtName", districtName);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
