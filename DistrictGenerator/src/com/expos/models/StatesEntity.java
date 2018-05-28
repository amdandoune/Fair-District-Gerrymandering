package com.expos.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "states", schema = "expos", catalog = "")
@IdClass(StatesEntityPK.class)
public class StatesEntity {
    private String stateName;
    private int year;
    private Integer visited;

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
    @Column(name = "visited")
    public Integer getVisited() {
        return visited;
    }

    public void setVisited(Integer visited) {
        this.visited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatesEntity that = (StatesEntity) o;
        return year == that.year &&
                Objects.equals(stateName, that.stateName) &&
                Objects.equals(visited, that.visited);
    }

    @Override
    public int hashCode() {

        return Objects.hash(stateName, year, visited);
    }
}
