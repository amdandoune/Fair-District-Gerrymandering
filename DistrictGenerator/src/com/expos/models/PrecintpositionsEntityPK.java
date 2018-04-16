package com.expos.models;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PrecintpositionsEntityPK implements Serializable {
    private String precinctName;
    private double position;
    private String stateName;

    @Column(name = "precinctName")
    @Id
    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    @Column(name = "position")
    @Id
    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    @Column(name = "stateName")
    @Id
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrecintpositionsEntityPK that = (PrecintpositionsEntityPK) o;
        return Double.compare(that.position, position) == 0 &&
                Objects.equals(precinctName, that.precinctName) &&
                Objects.equals(stateName, that.stateName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(precinctName, position, stateName);
    }
}
