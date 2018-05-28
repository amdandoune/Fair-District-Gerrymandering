package com.expos.dao;

import com.expos.models.PrecinctsEntity;

import java.util.List;

public interface PrecinctDao {

    List<PrecinctsEntity> list(String stateName, int year, String districtName);
}
