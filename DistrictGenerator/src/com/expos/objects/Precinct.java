package com.expos.objects;

import com.expos.models.PrecinctsEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Precinct {
    private String id;
    private String precinctName;
    private String stateName;
    private String districtName;
    private int year;
    private int population;
    private int republicans;
    private int democrats;
    private int other;
    private String winParty;
    private int whites;
    private int blacks;
    private int otherRaces;
    private List<Precinct> adjacentPrecincts;
    private List<double[]> points;

    public Precinct(PrecinctsEntity precinctsEntity) {
        this.setPrecinctName(precinctsEntity.getPrecinctName());
        this.setStateName(precinctsEntity.getStateName());
        this.setDistrictName(precinctsEntity.getDistrictName());
        this.setYear(precinctsEntity.getYear());
        this.setPopulation(precinctsEntity.getPopulation());
        this.setRepublicans(precinctsEntity.getRepublicans());
        this.setDemocrats(precinctsEntity.getDemocrats());
        this.setOther(precinctsEntity.getOther());
        this.setId(precinctsEntity.getId());
        this.setWhites(precinctsEntity.getWhites());
        this.setBlacks(precinctsEntity.getBlacks());
        this.setOtherRaces(precinctsEntity.getOtherRaces());
        this.setWinParty();
    }

    public Precinct(Precinct precinct) {
        this.id = precinct.getId();
        this.precinctName = precinct.getPrecinctName();
        this.stateName = precinct.getStateName();
        this.districtName = precinct.getDistrictName();
        this.year = precinct.getYear();
        this.population = precinct.getPopulation();
        this.republicans = precinct.getRepublicans();
        this.democrats = precinct.getDemocrats();
        this.other = precinct.getOther();
        this.whites = precinct.getWhites();
        this.blacks = precinct.getBlacks();
        this.otherRaces = precinct.getOtherRaces();
        this.winParty = precinct.getWinParty();
        this.adjacentPrecincts = precinct.getAdjacentPrecincts();
        this.points = precinct.getPoints();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getRepublicans() {
        return republicans;
    }

    public void setRepublicans(int republicans) {
        this.republicans = republicans;
    }

    public int getDemocrats() {
        return democrats;
    }

    public void setDemocrats(int democrats) {
        this.democrats = democrats;
    }

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }

    public int getWhites() {
        return whites;
    }

    public void setWhites(int whites) {
        this.whites = whites;
    }

    public int getBlacks() {
        return blacks;
    }

    public void setBlacks(int blacks) {
        this.blacks = blacks;
    }

    public int getOtherRaces() {
        return otherRaces;
    }

    public void setOtherRaces(int otherRaces) {
        this.otherRaces = otherRaces;
    }

    public void setWinParty() {
        if (getDemocrats() >= getRepublicans() && getDemocrats() >= getOther()) {
            winParty = "Democrats";
        } else if (getRepublicans() >= getDemocrats() && getRepublicans() >= getOther()) {
            winParty = "Republicans";
        } else if (getOther() >= getDemocrats() && getOther() >= getRepublicans()) {
            winParty = "Other Party";
        }
    }

    public String getWinParty() {
        return winParty;
    }

    public List<Precinct> getAdjacentPrecincts() {
        return adjacentPrecincts;
    }

    public void setAdjacentPrecincts(List<Precinct> adjacentPrecincts) {
        this.adjacentPrecincts = adjacentPrecincts;
    }

    public List<double[]> getPoints() {
        return points;
    }

    public void setPoints(List<double[]> points) {
        this.points = points;
    }

    @Override
    public Precinct clone(){
        ArrayList<Precinct> clonedAdjacentPrecincts = new ArrayList<>(adjacentPrecincts);
        ArrayList<double[]> clonedPoints = new ArrayList<>(points);
        Precinct clonedPrecinct = new Precinct(this);
        return clonedPrecinct;
    }
}