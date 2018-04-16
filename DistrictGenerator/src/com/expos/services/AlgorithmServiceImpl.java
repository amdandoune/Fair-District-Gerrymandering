package com.expos.services;

import com.expos.dao.StateDao;
import com.expos.models.StatesEntity;
import com.expos.models.StatesEntityPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {

    ModelAndView mv;
    @Autowired
    StateDao stateDao;

    @Transactional
    public StatesEntity getState() {
        StatesEntityPK stateKey = new StatesEntityPK();
        stateKey.setStateName("Maryland");
        stateKey.setYear(2010);
        return stateDao.get(stateKey);
    }

    @Transactional
    public List test(int year) {
        return stateDao.get2(year);
    }

}
