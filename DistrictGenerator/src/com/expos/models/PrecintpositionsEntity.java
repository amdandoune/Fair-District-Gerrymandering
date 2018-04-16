package com.expos.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "precintpositions", schema = "expos", catalog = "")
@IdClass(PrecintpositionsEntityPK.class)
public class PrecintpositionsEntity {
    private String precinctName;
    private double position;
    private String stateName;

    @Id
    @Column(name = "precinctName")
    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    @Id
    @Column(name = "position")
    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    @Id
    @Column(name = "stateName")
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
        PrecintpositionsEntity that = (PrecintpositionsEntity) o;
        return Double.compare(that.position, position) == 0 &&
                Objects.equals(precinctName, that.precinctName) &&
                Objects.equals(stateName, that.stateName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(precinctName, position, stateName);
    }
}
