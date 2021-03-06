package com.expos.dao;

import com.expos.models.StatesEntity;
import com.expos.models.StatesEntityPK;

import java.util.List;

public interface StateDao {

    StatesEntity get(StatesEntityPK stateName);

    void update(StatesEntity state);

}
