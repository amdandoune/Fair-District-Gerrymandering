package com.expos.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DistrictsEntityPK implements Serializable {
    private String districtName;
    private String stateName;
    private int year;

    @Column(name = "districtName")
    @Id
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
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
        DistrictsEntityPK that = (DistrictsEntityPK) o;
        return year == that.year &&
                Objects.equals(districtName, that.districtName) &&
                Objects.equals(stateName, that.stateName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(districtName, stateName, year);
    }
}
