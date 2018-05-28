package com.expos.objects;

import com.expos.models.StatesEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class State {
    private String stateName;
    private int year;

    private int population;
    private int republicans;
    private int democrats;
    private int other;
    private int whites;
    private int blacks;
    private int otherRaces;

    private String winParty;

    private double polsbyPopper;
    private double schwartzberg;
    private double reock;
    private double areaConvexHull;
    private double efficiencyGap;
    private double equalVoteWeight;
    private double equalPopulation;
    private double racialFairness;
    private boolean contiguity;
    private boolean preservation;

    private List<District> districts;

    public State(State state) {
        this.stateName = state.getStateName();
        this.year = state.getYear();
        this.population = state.getPopulation();
        this.republicans = state.getRepublicans();
        this.democrats = state.getDemocrats();
        this.other = state.getOther();
        this.whites = state.getWhites();
        this.blacks = state.getBlacks();
        this.otherRaces = state.getOtherRaces();
        this.winParty = state.getWinParty();
        this.polsbyPopper = state.getPolsbyPopper();
        this.schwartzberg = state.getSchwartzberg();
        this.reock = state.getReock();
        this.areaConvexHull = state.getAreaConvexHull();
        this.efficiencyGap = state.getEfficiencyGap();
        this.equalVoteWeight = state.getEqualVoteWeight();

        this.equalPopulation = state.getEqualPopulation();
        this.racialFairness = state.getRacialFairness();
        this.contiguity = state.isContiguity();
        this.preservation = state.isPreservation();
        this.districts = state.getDistricts();
    }

    public State(StatesEntity statesEntity) {
        this.stateName = statesEntity.getStateName();
        this.year = statesEntity.getYear();
    }


    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public int getPopulation() {
        return population;
    }

    public void setPopulation() {
        population = 0;
        for (District district : districts) {
            population += district.getPopulation();
        }
    }

    public Integer getRepublicans() {
        return republicans;
    }

    public void setRepublicans() {
        republicans = 0;
        for (District district : districts) {
            republicans += district.getRepublicans();
        }
    }

    public Integer getDemocrats() {
        return democrats;
    }

    public void setDemocrats() {
        democrats = 0;
        for (District district : districts) {
            democrats += district.getDemocrats();
        }
    }

    public Integer getOther() {
        return other;
    }

    public void setOther() {
        other = 0;
        for (District district : districts) {
            other += district.getOther();
        }
    }


    public Integer getWhites() {
        return whites;
    }

    public void setWhites() {
        whites = 0;
        for (District district : districts) {
            whites += district.getWhites();
        }
    }

    public Integer getBlacks() {
        return blacks;
    }

    public void setBlacks() {
        blacks = 0;
        for (District district : districts) {
            blacks += district.getBlacks();
        }
    }

    public Integer getOtherRaces() {
        return otherRaces;
    }

    public void setOtherRaces() {
        otherRaces = 0;
        for (District district : districts) {
            otherRaces += district.getOtherRaces();
        }
    }


    public void setWinParty() {

        for (District district : districts)
            district.setWinParty();
        int demDistricts = 0;
        int repDistricts = 0;
        int othDistricts = 0;
        for (District district : districts) {
            if (district.getWinParty().equals("Democrats")) {
                demDistricts += 1;
            } else if (district.getWinParty().equals("Republicans")) {
                repDistricts += 1;
            } else if (district.getWinParty().equals("Other Party")) {
                othDistricts += 1;
            }
        }
        if (demDistricts >= repDistricts && demDistricts >= othDistricts) {
            winParty = "Democrats";
        } else if (repDistricts >= demDistricts && repDistricts >= othDistricts) {
            winParty = "Republicans";
        } else if (othDistricts >= demDistricts && othDistricts >= repDistricts) {
            winParty = "Other Party";
        }
    }

    public String getWinParty() {
        return winParty;
    }

    public void setAll() {
        setPopulation();
        setDemocrats();
        setRepublicans();
        setOther();
        setWhites();
        setBlacks();
        setOtherRaces();
    }


    public double getPolsbyPopper() {
        return polsbyPopper;
    }

    public void setPolsbyPopper(double polsbyPopper) {
        this.polsbyPopper = polsbyPopper;
    }

    public double getSchwartzberg() {
        return schwartzberg;
    }

    public void setSchwartzberg(double schwartzberg) {
        this.schwartzberg = schwartzberg;
    }

    public double getReock() {
        return reock;
    }

    public void setReock(double reock) {
        this.reock = reock;
    }

    public double getEfficiencyGap() {
        return efficiencyGap;
    }

    public void setEfficiencyGap(double efficiencyGap) {
        this.efficiencyGap = efficiencyGap;
    }

    public double getEqualPopulation() {
        return equalPopulation;
    }

    public void setEqualPopulation(double equalPopulation) {
        this.equalPopulation = equalPopulation;
    }

    public double getRacialFairness() {
        return racialFairness;
    }

    public void setRacialFairness(double racialFairness) {
        this.racialFairness = racialFairness;
    }

    public boolean isContiguity() {
        return contiguity;
    }

    public void setContiguity(boolean contiguity) {
        this.contiguity = contiguity;
    }

    public boolean isPreservation() {
        return preservation;
    }

    public void setPreservation(boolean preservation) {
        this.preservation = preservation;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
        setAll();
    }

    public void processDistrictBorders() {
        for (District district : districts) {
            district.setBorderPrecincts();
        }
    }

    public District getDistrict(String districtName) {
        for (District district : districts) {
            if (district.getDistrictName().equals(districtName)) {
                return district;
            }
        }
        return null;
    }

    public Precinct getPrecinct(String id) {
        for (District district : districts) {
            for (Precinct precinct : district.getPrecincts()) {
                if (precinct.getId().equals(id))
                    return precinct;
            }
        }
        return null;
    }

    public District getRandomDistrict() {
        Random random = new Random();
        int randomNum = random.nextInt((districts.size() - 1) - 0);
        return districts.get(randomNum);
    }

    public String[] movePrecinct(District randomDistrict, List<String> bannedPrecincts, List<String> bannedDistricts) {
        String[] array = new String[3];
        Collections.shuffle(randomDistrict.getBorderPrecincts());
        for (Precinct randomBorderPrecinct : randomDistrict.getBorderPrecincts()) {
            if (!bannedPrecincts.contains(randomBorderPrecinct.getId())) {
                Collections.shuffle(randomBorderPrecinct.getAdjacentPrecincts());
                for (Precinct randomAdjacentPrecinct : randomBorderPrecinct.getAdjacentPrecincts()) {
                    if (!(randomBorderPrecinct.getDistrictName().equals(randomAdjacentPrecinct.getDistrictName()))) {
                        District district = getDistrict(randomAdjacentPrecinct.getDistrictName());
                        if (bannedDistricts.contains(district.getDistrictName()))
                            return null;
                        array[0] = randomBorderPrecinct.getId();
                        array[1] = randomAdjacentPrecinct.getDistrictName();
                        array[2] = randomBorderPrecinct.getDistrictName();
                        randomBorderPrecinct.setDistrictName(district.getDistrictName());
                        district.getPrecincts().add(randomBorderPrecinct);
                        district.setBorderPrecincts();
                        randomDistrict.removePrecinct(array[0]);
                        randomDistrict.setBorderPrecincts();
                        return array;
                    }
                }
            }
        }
        return null;
    }

    public double calculateEqualVoteWeight(){
        ArrayList<Double> voteWeights = new ArrayList<>();
        for (District district : districts){
            double percentageDemocratic = (double) district.getDemocrats() / ((double) district.getDemocrats() + (double) district.getRepublicans());
            voteWeights.add(percentageDemocratic);
        }
        double sum = 0;
        for (double weight : voteWeights){
            sum += weight;
        }
        double average = sum / voteWeights.size();
        int medianIndex = voteWeights.size() / 2;
        Collections.sort(voteWeights);
        double median = voteWeights.get(medianIndex);
        return Math.abs(average - median);
    }

    public void resetObjectiveFunctions() {
        polsbyPopper = 0;
        schwartzberg = 0;
        reock = 0;
        areaConvexHull = 0;
        efficiencyGap = 0;
        equalVoteWeight = 0;
        equalPopulation = 0;
        racialFairness = 0;
        contiguity = true;
        preservation = true;
    }

    public void setObjectiveMeasures() {
        resetObjectiveFunctions();
        for (District district : districts) {
            polsbyPopper += district.getPolsbyPopper();
            schwartzberg += district.getSchwartzberg();
            reock += district.getReock();
            areaConvexHull += district.getAreaConvexHull();
            efficiencyGap += district.getEfficiencyGap();
            equalPopulation += district.getEqualPopulation();
            racialFairness += district.getRacialFairness();
        }
        polsbyPopper = polsbyPopper / (double) districts.size();
        schwartzberg = schwartzberg / (double) districts.size();
        reock = reock / (double) districts.size();
        areaConvexHull = areaConvexHull / (double) districts.size();
        efficiencyGap = efficiencyGap / (double) districts.size();
        equalPopulation = equalPopulation / (double) districts.size();
        racialFairness = racialFairness / (double) districts.size();
        efficiencyGap = Math.abs(efficiencyGap);
        equalVoteWeight = calculateEqualVoteWeight();
    }

    public double getAreaConvexHull() {
        return areaConvexHull;
    }

    public void setAreaConvexHull(double areaConvexHull) {
        this.areaConvexHull = areaConvexHull;
    }

    public double getEqualVoteWeight() {
        return equalVoteWeight;
    }

    public void setEqualVoteWeight(double equalVoteWeight) {
        this.equalVoteWeight = equalVoteWeight;
    }

    @Override
    public State clone(){
        State state = new State(this);
        ArrayList<District> clonedDistricts = new ArrayList<>();
        for (District district : state.getDistricts()){
            clonedDistricts.add(district.clone());
        }
        state.setDistricts(clonedDistricts);
        return state;
    }
}