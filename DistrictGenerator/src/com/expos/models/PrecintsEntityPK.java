package com.expos.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PrecintsEntityPK implements Serializable {
    private String precinctName;
    private String stateName;
    private int year;

    @Column(name = "precinctName")
    @Id
    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    @Column(name = "stateName")
    @Id
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Column(name = "year")
    @Id
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrecintsEntityPK that = (PrecintsEntityPK) o;
        return year == that.year &&
                Objects.equals(precinctName, that.precinctName) &&
                Objects.equals(stateName, that.stateName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(precinctName, stateName, year);
    }
}
