package com.expos.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "districts", schema = "expos", catalog = "")
@IdClass(DistrictsEntityPK.class)
public class DistrictsEntity {
    private String districtName;
    private String stateName;
    private int year;
    private String id;
    private String democrateRep;
    private String republicanRep;
    private String otherPartyRep;

    @Id
    @Column(name = "districtName")
    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

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
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "democrateRep")
    public String getDemocrateRep() {
        return democrateRep;
    }

    public void setDemocrateRep(String democrateRep) {
        this.democrateRep = democrateRep;
    }

    @Basic
    @Column(name = "republicanRep")
    public String getRepublicanRep() {
        return republicanRep;
    }

    public void setRepublicanRep(String republicanRep) {
        this.republicanRep = republicanRep;
    }

    @Basic
    @Column(name = "otherPartyRep")
    public String getOtherPartyRep() {
        return otherPartyRep;
    }

    public void setOtherPartyRep(String otherPartyRep) {
        this.otherPartyRep = otherPartyRep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictsEntity that = (DistrictsEntity) o;
        return year == that.year &&
                Objects.equals(districtName, that.districtName) &&
                Objects.equals(stateName, that.stateName) &&
                Objects.equals(id, that.id) &&
                Objects.equals(democrateRep, that.democrateRep) &&
                Objects.equals(republicanRep, that.republicanRep) &&
                Objects.equals(otherPartyRep, that.otherPartyRep);
    }

    @Override
    public int hashCode() {

        return Objects.hash(districtName, stateName, year, id, democrateRep, republicanRep, otherPartyRep);
    }
}
