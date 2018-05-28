package com.expos.services;

import com.expos.dao.*;
import com.expos.models.*;
import com.expos.objects.District;
import com.expos.objects.Precinct;
import com.expos.objects.State;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileReader;
import java.util.*;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    ModelAndView mv;
    @Autowired
    StateDao stateDao;

    @Autowired
    DistrictDao districtDao;

    @Autowired
    PrecinctDao precinctDao;

    @Autowired
    private ResourceLoader resourceLoader;
    @Value("${maryland_path}")
    private String marylandPath;
    @Value("${westvirginia_path}")
    private String westvirginiaPath;
    @Value("${missouri_path}")
    private String missouriPath;

    @Transactional
    public StatesEntity getStateEntity(String stateName, int year) {
        StatesEntityPK stateKey = new StatesEntityPK();
        stateKey.setStateName(stateName);
        stateKey.setYear(year);
        return stateDao.get(stateKey);
    }

    @Transactional
    public State getState(String stateName, int year) {
        StatesEntity statesEntity = getStateEntity(stateName, year);
        stateDao.update(statesEntity);
        State state = new State(statesEntity);
        return state;
    }

    @Transactional
    public List<DistrictsEntity> getDistrictEntities(String stateName, int year) {
        List list = districtDao.list(stateName, year);
        return list;
    }

    @Transactional
    public List<District> getDistricts(String stateName, int year) {
        List<DistrictsEntity> districtEntityList = getDistrictEntities(stateName, year);
        List<District> list = new ArrayList<District>();
        for (DistrictsEntity districtEntity : districtEntityList) {
            District district = new District(districtEntity);
            district.setPrecincts(getPrecincts(districtEntity.getStateName(), districtEntity.getYear(), districtEntity.getDistrictName()));
            list.add(district);
        }
        return list;
    }

    @Transactional
    public List<PrecinctsEntity> getPrecinctEntities(String stateName, int year, String districtName) {
        List list = precinctDao.list(stateName, year, districtName);
        return list;
    }

    @Transactional
    public List<Precinct> getPrecincts(String stateName, int year, String districtName) {
        List<PrecinctsEntity> precinctsEntityList = getPrecinctEntities(stateName, year, districtName);
        List<Precinct> list = new ArrayList<Precinct>();
        for (PrecinctsEntity precinctsEntity : precinctsEntityList) {
            Precinct precinct = new Precinct(precinctsEntity);
            list.add(precinct);
        }
        return list;
    }

    public void calculateObjectiveFunctions(State state, Map<String, Integer> weights) {
        for (District district : state.getDistricts()) {
            calculateObjectiveFunctions(district, state, weights);
        }
        state.setAll();
        state.setEqualVoteWeight(state.calculateEqualVoteWeight());
    }

    public void calculateObjectiveFunctions(District district, State state, Map<String, Integer> weights) {
        district.setAll();
        if (weights.get("polsbyPopper") != 0) {
            district.setPolsbyPopper(calculatePolsbyPopper(district));
        } else {
            district.setPolsbyPopper(0);
        }
        if (weights.get("schwartzberg") != 0) {
            district.setSchwartzberg(calculateSchwartzberg(district));
        } else {
            district.setSchwartzberg(0);
        }
        district.setReock(calculateReock(district));
        district.setAreaConvexHull(calculateAreaConvexHull(district));
        district.setEfficiencyGap(calculateEfficiencyGap(district));
        district.setEqualPopulation(calculateEqualPopulation(district, (double) state.getPopulation() / (double) state.getDistricts().size()));
        district.setRacialFairness(calculateRacialFairness(district, state));
    }

    public double calculatePolsbyPopper(District district) {
        return ((4 * Math.PI) * district.getArea() / (district.getPerimeter() * 2));
    }

    public double calculateSchwartzberg(District district) {
        return 1 / (district.getPerimeter() / (2 * Math.PI * (Math.sqrt(district.getArea() / Math.PI))));
    }

    public double calculateReock(District district) {
        return district.getArea() / district.getBoundingCircle();
    }

    public double calculateAreaConvexHull(District district) {
        return district.getArea() / district.convexHullArea();
    }

    public double calculateEfficiencyGap(District district) {
        int average = (district.getRepublicans() + district.getDemocrats()) / 2;
        int wastedDemocraticVotes;
        int wastedRepublicanVotes;
        if (district.getDemocrats() > average) {
            wastedDemocraticVotes = district.getDemocrats() - average;
        } else {
            wastedDemocraticVotes = district.getDemocrats();
        }
        if (district.getRepublicans() > average) {
            wastedRepublicanVotes = district.getRepublicans() - average;
        } else {
            wastedRepublicanVotes = district.getRepublicans();
        }
        double DemocraticWaste = (double) wastedDemocraticVotes / (double) district.getDemocrats();
        double RepublicanWaste = (double) wastedRepublicanVotes / (double) district.getRepublicans();
        return RepublicanWaste - DemocraticWaste;
    }

    public double calculateEqualPopulation(District district, double averagePopulation) {
        double result = 0;
        result = 1 - (Math.abs(averagePopulation - district.getPopulation()) / averagePopulation);
        return result;
    }

    public double calculateRacialFairness(District district, State state) {
        double averageWP = (double) state.getWhites() / (double) state.getPopulation();
        double districtWP = (double) district.getWhites() / (double) district.getPopulation();
        double averageBP = (double) state.getBlacks() / (double) state.getPopulation();
        double districtBP = (double) district.getBlacks() / (double) district.getPopulation();
        double averageOP = 1.00 - (averageWP + averageBP);
        double districtOP = 1.00 - (districtWP + districtBP);
        double wpFairness = Math.abs(averageWP - districtWP);
        double bpFairness = Math.abs(averageBP - districtBP);
        double opFairness = Math.abs(averageOP - districtOP);
        return (wpFairness + bpFairness + opFairness) / 3;
    }

    public boolean calculateContiguity(District district) {
        return false;
    }

    public boolean calculatePreservation(District district) {
        return false;
    }

    public double calculateDifference(double number, double origin) {
        double result = 0;
        result = Math.abs((number - origin) / origin);
        return result;
    }

    public State getBetterState(State oldState, State newState, Map<String, Integer> weights) {
        double weightedPolsbyPopperDiff = (newState.getPolsbyPopper() - oldState.getPolsbyPopper()) * (double) weights.get("polsbyPopper");
        double weightedSchwartzbergDiff = (newState.getSchwartzberg() - oldState.getSchwartzberg()) * (double) weights.get("schwartzberg");
        double weightedReockDiff = (newState.getReock() - oldState.getReock()) * (double) weights.get("reock");
        double weightedAreaConvexHullDiff = (newState.getAreaConvexHull() - oldState.getAreaConvexHull()) * (double) weights.get("areaConvexHull");
        double weightedEfficiencyGap = ((1 - newState.getEfficiencyGap()) - (1 - oldState.getEfficiencyGap())) * (double) weights.get("efficiencyGap");
        double equalVoteWeightDiff = ((1 - newState.getEqualVoteWeight()) - (1 - oldState.getEqualVoteWeight())) * (double) weights.get("efficiencyGap");
        double weightedEqualPopulationDiff = (newState.getEqualPopulation() - oldState.getEqualPopulation()) * (double) weights.get("equalPopulation");
        double weightedRacialFairnessDiff = (1-(newState.getRacialFairness()) - (1 - oldState.getRacialFairness())) * (double) weights.get("racialFairness");
        double weightedSum = weightedPolsbyPopperDiff + weightedSchwartzbergDiff + weightedReockDiff + weightedAreaConvexHullDiff + weightedEfficiencyGap + equalVoteWeightDiff + weightedEqualPopulationDiff + weightedRacialFairnessDiff;
        if (weightedSum >= 0)
            return newState;
        else
            return oldState;
    }

    public void parseGeoJson(State state) {
        String fileName = null;
        switch (state.getStateName()) {
            case "Maryland":
                fileName = marylandPath;
                break;
            case "West Virginia":
                fileName = westvirginiaPath;
                break;
            case "Missouri":
                fileName = missouriPath;
                break;
        }
        JSONParser parser = new JSONParser();
        try {
            File file = new File(resourceLoader.getResource("/res/js/" + fileName + ".json").getURI());
            Object object = parser.parse(new FileReader(file.getAbsolutePath()));
            JSONObject rootJSON = (JSONObject) object;
            JSONArray featuresList = (JSONArray) rootJSON.get("features");
            for (Object featureObj : featuresList.toArray()) {
                JSONObject feature = (JSONObject) featureObj;
                JSONObject properties = (JSONObject) feature.get("properties");
                JSONObject geometry = (JSONObject) feature.get("geometry");
                String precinctId = properties.get("GEOID10").toString();
                for (District district : state.getDistricts()) {
                    for (Precinct precinct : district.getPrecincts()) {
                        if (precinct.getId().equals(precinctId)) {
                            List<double[]> list = new ArrayList<double[]>();
                            String coordinates = geometry.get("coordinates").toString();
                            String[] coordinateTokens = coordinates.split("],\\[", 0);
                            for (String token : coordinateTokens) {
                                token = token.replaceAll("\\[|]", "");
                                String[] coords = token.split(",", 0);
                                double[] arr = new double[2];
                                int counter = 0;
                                for (String coord : coords) {
                                    arr[counter++] = Double.parseDouble(coord);
                                }
                                list.add(arr);
                            }
                            precinct.setPoints(list);
                            List<Precinct> adjList = new ArrayList<Precinct>();
                            String adjacentPrecincts = properties.get("adjacentPrecincts").toString();
                            String[] adjPrecinctTokens = adjacentPrecincts.split("],\\[", 0);
                            for (String token : adjPrecinctTokens) {
                                token = token.replaceAll("\\[|]|\"", "").trim();
                                String[] adj = token.split(",", 0);
                                if (adj.length >= 2) {
                                    Precinct adjacent = state.getPrecinct(adj[1]);
                                    adjList.add(adjacent);
                                }
                            }
                            precinct.setAdjacentPrecincts(adjList);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

