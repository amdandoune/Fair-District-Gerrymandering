package com.expos.services;

import com.expos.models.DistrictsEntity;
import com.expos.models.PrecinctsEntity;
import com.expos.models.StatesEntity;
import com.expos.objects.District;
import com.expos.objects.Precinct;
import com.expos.objects.State;

import java.util.List;
import java.util.Map;

public interface AlgorithmService {

    StatesEntity getStateEntity(String stateName, int year);

    State getState(String stateName, int year);

    List<DistrictsEntity> getDistrictEntities(String stateName, int year);

    List<District> getDistricts(String stateName, int year);

    List<PrecinctsEntity> getPrecinctEntities(String stateName, int year, String districtName);

    List<Precinct> getPrecincts(String stateName, int year, String districtName);

    void calculateObjectiveFunctions(State state, Map<String, Integer> weights);

    void calculateObjectiveFunctions(District district, State state, Map<String, Integer> weights);

    double calculatePolsbyPopper(District district);

    double calculateSchwartzberg(District district);

    double calculateReock(District district);

    double calculateEfficiencyGap(District district);

    double calculateEqualPopulation(District district, double averagePopulation);

    double calculateRacialFairness(District district, State state);

    boolean calculateContiguity(District district);

    boolean calculatePreservation(District district);

    double calculateDifference(double number, double origin);

    State getBetterState(State oldState, State newState, Map<String, Integer> weights);

    void parseGeoJson(State state);


}
