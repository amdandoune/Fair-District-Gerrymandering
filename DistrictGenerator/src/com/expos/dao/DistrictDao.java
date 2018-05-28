package com.expos.dao;

import com.expos.models.DistrictsEntity;

import java.util.List;

public interface DistrictDao {

    List<DistrictsEntity> list(String stateName, int year);

}
