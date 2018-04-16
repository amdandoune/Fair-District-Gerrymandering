package com.expos.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "states", schema = "expos", catalog = "")
@IdClass(StatesEntityPK.class)
public class StatesEntity {
    private String stateName;
    private int year;
    private Integer population;
    private Integer republicans;
    private Integer democrats;
    private Integer other;

    @Id
    @Column(name = "stateName")
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatesEntity that = (StatesEntity) o;
        return year == that.year &&
                Objects.equals(stateName, that.stateName) &&
                Objects.equals(population, that.population) &&
                Objects.equals(republicans, that.republicans) &&
                Objects.equals(democrats, that.democrats) &&
                Objects.equals(other, that.other);
    }

    @Override
    public int hashCode() {

        return Objects.hash(stateName, year, population, republicans, democrats, other);
    }
}
