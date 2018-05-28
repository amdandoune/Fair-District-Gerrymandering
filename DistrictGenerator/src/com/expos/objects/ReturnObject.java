package com.expos.objects;

public class ReturnObject {

    private String id;
    private String districtName;
    private String districtId;
    private double polsbyPopper;
    private double schwartzberg;
    private double reock;
    private double areaConvexHull;
    private double efficiencyGap;
    private double equalPopulation;
    private double racialFairness;
    private String prevDistrictName;
    private double prevPolsbyPopper;
    private double prevSchwartzberg;
    private double prevReock;
    private double prevAreaConvexHull;
    private double prevEfficiencyGap;
    private double prevEqualPopulation;
    private double prevRacialFairness;
    private double statePolsbyPopper;
    private double stateSchwartzberg;
    private double stateReock;
    private double stateAreaConvexHull;
    private double stateEfficiencyGap;
    private double stateEqualVoteWeight;
    private double stateEqualPopulation;
    private double stateRacialFairness;
    private int termination;


    public ReturnObject() {
        id = "";
        districtName = "";
        districtId = "";
        polsbyPopper = 0;
        schwartzberg = 0;
        reock = 0;
        areaConvexHull = 0;
        efficiencyGap = 0;
        equalPopulation = 0;
        racialFairness = 0;
        prevDistrictName = "";
        prevPolsbyPopper = 0;
        prevSchwartzberg = 0;
        prevReock = 0;
        prevAreaConvexHull = 0;
        prevEfficiencyGap = 0;
        prevEqualPopulation = 0;
        prevRacialFairness = 0;
        statePolsbyPopper = 0;
        stateSchwartzberg = 0;
        stateReock = 0;
        stateAreaConvexHull = 0;
        stateEfficiencyGap = 0;
        stateEqualVoteWeight = 0;
        stateEqualPopulation = 0;
        stateRacialFairness = 0;
        termination = 0;
    }

    public ReturnObject(String id, String districtName, String districtId, double polsbyPopper, double schwartzberg,
                        double reock, double areaConvexHull, double efficiencyGap, double equalPopulation, double racialFairness,
                        String prevDistrictName, double prevPolsbyPopper, double prevSchwartzberg, double prevReock, double prevAreaConvexHull,
                        double prevEfficiencyGap, double prevEqualPopulation, double prevRacialFairness, double statePolsbyPopper,
                        double stateSchwartzberg, double stateReock, double stateAreaConvexHull, double stateEfficiencyGap, double stateEqualVoteWeight, double stateEqualPopulation, double stateRacialFairness,
                        int termination) {
        this.id = id;
        this.districtName = districtName;
        this.districtId = districtId;
        this.polsbyPopper = polsbyPopper;
        this.schwartzberg = schwartzberg;
        this.reock = reock;
        this.areaConvexHull = areaConvexHull;
        this.efficiencyGap = efficiencyGap;
        this.equalPopulation = equalPopulation;
        this.racialFairness = racialFairness;
        this.prevDistrictName = prevDistrictName;
        this.prevPolsbyPopper = prevPolsbyPopper;
        this.prevSchwartzberg = prevSchwartzberg;
        this.prevReock = prevReock;
        this.prevAreaConvexHull = prevAreaConvexHull;
        this.prevEfficiencyGap = prevEfficiencyGap;
        this.prevEqualPopulation = prevEqualPopulation;
        this.prevRacialFairness = prevRacialFairness;
        this.statePolsbyPopper = statePolsbyPopper;
        this.stateSchwartzberg = stateSchwartzberg;
        this.stateReock = stateReock;
        this.stateAreaConvexHull = stateAreaConvexHull;
        this.stateEfficiencyGap = stateEfficiencyGap;
        this.stateEqualVoteWeight = stateEqualVoteWeight;
        this.stateEqualPopulation = stateEqualPopulation;
        this.stateRacialFairness = stateRacialFairness;
        this.termination = termination;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
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

    public String getPrevDistrictName() {
        return prevDistrictName;
    }

    public void setPrevDistrictName(String prevDistrictName) {
        this.prevDistrictName = prevDistrictName;
    }

    public double getPrevPolsbyPopper() {
        return prevPolsbyPopper;
    }

    public void setPrevPolsbyPopper(double prevPolsbyPopper) {
        this.prevPolsbyPopper = prevPolsbyPopper;
    }

    public double getPrevSchwartzberg() {
        return prevSchwartzberg;
    }

    public void setPrevSchwartzberg(double prevSchwartzberg) {
        this.prevSchwartzberg = prevSchwartzberg;
    }

    public double getPrevReock() {
        return prevReock;
    }

    public void setPrevReock(double prevReock) {
        this.prevReock = prevReock;
    }

    public double getPrevEfficiencyGap() {
        return prevEfficiencyGap;
    }

    public void setPrevEfficiencyGap(double prevEfficiencyGap) {
        this.prevEfficiencyGap = prevEfficiencyGap;
    }

    public double getPrevEqualPopulation() {
        return prevEqualPopulation;
    }

    public void setPrevEqualPopulation(double prevEqualPopulation) {
        this.prevEqualPopulation = prevEqualPopulation;
    }

    public double getPrevRacialFairness() {
        return prevRacialFairness;
    }

    public void setPrevRacialFairness(double prevRacialFairness) {
        this.prevRacialFairness = prevRacialFairness;
    }

    public double getStatePolsbyPopper() {
        return statePolsbyPopper;
    }

    public void setStatePolsbyPopper(double statePolsbyPopper) {
        this.statePolsbyPopper = statePolsbyPopper;
    }

    public double getStateSchwartzberg() {
        return stateSchwartzberg;
    }

    public void setStateSchwartzberg(double stateSchwartzberg) {
        this.stateSchwartzberg = stateSchwartzberg;
    }

    public double getStateReock() {
        return stateReock;
    }

    public void setStateReock(double stateReock) {
        this.stateReock = stateReock;
    }

    public double getStateEfficiencyGap() {
        return stateEfficiencyGap;
    }

    public void setStateEfficiencyGap(double stateEfficiencyGap) {
        this.stateEfficiencyGap = stateEfficiencyGap;
    }

    public double getStateEqualPopulation() {
        return stateEqualPopulation;
    }

    public void setStateEqualPopulation(double stateEqualPopulation) {
        this.stateEqualPopulation = stateEqualPopulation;
    }

    public double getStateRacialFairness() {
        return stateRacialFairness;
    }

    public void setStateRacialFairness(double stateRacialFairness) {
        this.stateRacialFairness = stateRacialFairness;
    }

    public int getTermination() {
        return termination;
    }

    public void setTermination(int termination) {
        this.termination = termination;
    }

    public double getPrevAreaConvexHull() {
        return prevAreaConvexHull;
    }

    public void setPrevAreaConvexHull(double prevAreaConvexHull) {
        this.prevAreaConvexHull = prevAreaConvexHull;
    }

    public double getStateAreaConvexHull() {
        return stateAreaConvexHull;
    }

    public void setStateAreaConvexHull(double stateAreaConvexHull) {
        this.stateAreaConvexHull = stateAreaConvexHull;
    }

    public double getStateEqualVoteWeight() {
        return stateEqualVoteWeight;
    }

    public void setStateEqualVoteWeight(double stateEqualVoteWeight) {
        this.stateEqualVoteWeight = stateEqualVoteWeight;
    }

    public double getAreaConvexHull() {
        return areaConvexHull;
    }

    public void setAreaConvexHull(double areaConvexHull) {
        this.areaConvexHull = areaConvexHull;
    }
}
