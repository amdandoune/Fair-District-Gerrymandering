package com.expos.services;

import com.expos.models.StatesEntity;
import com.expos.models.StatesEntityPK;

import java.util.List;

public interface AlgorithmService {

    StatesEntity getState();
    List test(int year);

}
