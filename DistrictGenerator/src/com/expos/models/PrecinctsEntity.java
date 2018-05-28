package com.expos.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "precincts", schema = "expos", catalog = "")
@IdClass(PrecinctsEntityPK.class)
public class PrecinctsEntity {
    private String precinctName;
    private String stateName;
    private String districtName;
    private int year;
    private Integer population;
    private Integer republicans;
    private Integer democrats;
    private Integer other;
    private String id;
    private Integer whites;
    private Integer blacks;
    private Integer otherRaces;

    @Basic
    @Column(name = "precinctName")
    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    @Basic
    @Column(name = "stateName")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Basic
    @Column(name = "districtName")
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Id
    @Column(name = "year")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Basic
    @Column(name = "population")
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Basic
    @Column(name = "republicans")
    public Integer getRepublicans() {
        return republicans;
    }

    public void setRepublicans(Integer republicans) {
        this.republicans = republicans;
    }

    @Basic
    @Column(name = "democrats")
    public Integer getDemocrats() {
        return democrats;
    }

    public void setDemocrats(Integer democrats) {
        this.democrats = democrats;
    }

    @Basic
    @Column(name = "other")
    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "whites")
    public Integer getWhites() {
        return whites;
    }

    public void setWhites(Integer whites) {
        this.whites = whites;
    }

    @Basic
    @Column(name = "blacks")
    public Integer getBlacks() {
        return blacks;
    }

    public void setBlacks(Integer blacks) {
        this.blacks = blacks;
    }

    @Basic
    @Column(name = "otherRaces")
    public Integer getOtherRaces() {
        return otherRaces;
    }

    public void setOtherRaces(Integer otherRaces) {
        this.otherRaces = otherRaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrecinctsEntity that = (PrecinctsEntity) o;
        return year == that.year &&
                Objects.equals(precinctName, that.precinctName) &&
                Objects.equals(stateName, that.stateName) &&
                Objects.equals(districtName, that.districtName) &&
                Objects.equals(population, that.population) &&
                Objects.equals(republicans, that.republicans) &&
                Objects.equals(democrats, that.democrats) &&
                Objects.equals(other, that.other) &&
                Objects.equals(id, that.id) &&
                Objects.equals(whites, that.whites) &&
                Objects.equals(blacks, that.blacks) &&
                Objects.equals(otherRaces, that.otherRaces);
    }

    @Override
    public int hashCode() {

        return Objects.hash(precinctName, stateName, districtName, year, population, republicans, democrats, other, id, whites, blacks, otherRaces);
    }
}
