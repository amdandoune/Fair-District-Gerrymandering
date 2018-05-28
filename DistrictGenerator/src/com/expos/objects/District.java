package com.expos.objects;

import com.expos.models.DistrictsEntity;
import com.vividsolutions.jts.geom.*;
import org.geotools.filter.AreaFunction;
import org.geotools.geometry.jts.JTSFactoryFinder;

import java.util.ArrayList;
import java.util.List;

public class District {
    private String districtName;
    private String stateName;
    private String id;
    private int year;
    private int population;
    private int republicans;
    private int democrats;
    private int other;

    private String winParty;
    private String representative;
    private String democrateRep;
    private String republicanRep;
    private String otherPartyRep;

    private int whites;
    private int blacks;
    private int otherRaces;

    private List<Precinct> precincts;
    private List<Precinct> borderPrecincts;

    private double polsbyPopper;
    private double schwartzberg;
    private double reock;
    private double areaConvexHull;
    private double efficiencyGap;
    private double equalPopulation;
    private double racialFairness;
    private boolean contiguity;
    private boolean preservation;

    private double X[];
    private double Y[];

    public District(DistrictsEntity districtsEntity) {
        this.setStateName(districtsEntity.getStateName());
        this.setYear(districtsEntity.getYear());
        this.setDistrictName(districtsEntity.getDistrictName());
        this.setId(districtsEntity.getId());
        this.setDemocrateRep(districtsEntity.getDemocrateRep());
        this.setRepublicanRep(districtsEntity.getRepublicanRep());
        this.setOtherPartyRep(districtsEntity.getOtherPartyRep());
    }

    public District(District district) {
        this.districtName = district.getDistrictName();
        this.stateName = district.getStateName();
        this.id = district.getId();
        this.year = district.getYear();
        this.population = district.getPopulation();
        this.republicans = district.getRepublicans();
        this.democrats = district.getDemocrats();
        this.other = district.getOther();
        this.whites = district.getWhites();
        this.blacks = district.getBlacks();
        this.otherRaces = district.getOtherRaces();
        this.precincts = district.getPrecincts();
        this.borderPrecincts = district.getBorderPrecincts();
        this.polsbyPopper = district.getPolsbyPopper();
        this.schwartzberg = district.getSchwartzberg();
        this.reock = district.getReock();
        this.efficiencyGap = district.getEfficiencyGap();
        this.equalPopulation = district.getEqualPopulation();
        this.racialFairness = district.getRacialFairness();
        this.contiguity = district.isContiguity();
        this.preservation = district.isPreservation();
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation() {
        population = 0;
        for (Precinct precinct : precincts) {
            population += precinct.getPopulation();
        }
    }

    public int getRepublicans() {
        return republicans;
    }

    public void setRepublicans() {
        republicans = 0;
        for (Precinct precinct : precincts) {
            republicans += precinct.getRepublicans();
        }
    }

    public int getDemocrats() {
        return democrats;
    }

    public void setDemocrats() {
        democrats = 0;
        for (Precinct precinct : precincts) {
            democrats += precinct.getDemocrats();
        }
    }

    public int getOther() {
        return other;
    }

    public void setOther() {
        other = 0;
        for (Precinct precinct : precincts) {
            other += precinct.getOther();
        }
    }

    public int getWhites() {
        return whites;
    }

    public void setWhites() {
        whites = 0;
        for (Precinct precinct : precincts) {
            whites += precinct.getWhites();
        }
    }

    public int getBlacks() {
        return blacks;
    }

    public void setBlacks() {
        blacks = 0;
        for (Precinct precinct : precincts) {
            blacks += precinct.getBlacks();
        }
    }

    public int getOtherRaces() {
        return otherRaces;
    }

    public void setOtherRaces() {
        otherRaces = 0;
        for (Precinct precinct : precincts) {
            otherRaces += precinct.getOtherRaces();
        }
    }

    public void setWinParty() {
        int demPrecincts = 0;
        int repPrecincts = 0;
        int othPrecincts = 0;
        for (Precinct precinct : precincts) {
            if (precinct.getWinParty().equals("Democrats")) {
                demPrecincts += 1;
            } else if (precinct.getWinParty().equals("Republicans")) {
                repPrecincts += 1;
            } else if (precinct.getWinParty().equals("Other Party")) {
                othPrecincts += 1;
            }
        }
        if (demPrecincts >= repPrecincts && demPrecincts >= othPrecincts) {
            winParty = "Democrats";
        } else if (repPrecincts >= demPrecincts && repPrecincts >= othPrecincts) {
            winParty = "Republicans";
        } else if (othPrecincts >= demPrecincts && othPrecincts >= repPrecincts) {
            winParty = "Other Party";
        }
    }

    public void setAll(){
        setPopulation();
        setDemocrats();
        setRepublicans();
        setOther();
        setWhites();
        setBlacks();
        setOtherRaces();
    }

    public String getWinParty() {
        return winParty;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative() {
        if (winParty.equals("Democrats")) {
            representative = getDemocrateRep();
        } else if (winParty.equals("Republicans")) {
            representative = getRepublicanRep();
        } else if (winParty.equals("Other Party")) {
            representative = getOtherPartyRep();
        }
    }

    public String getDemocrateRep() {
        return democrateRep;
    }

    public void setDemocrateRep(String democrateRep) {
        this.democrateRep = democrateRep;
    }

    public String getRepublicanRep() {
        return republicanRep;
    }

    public void setRepublicanRep(String republicanRep) {
        this.republicanRep = republicanRep;
    }

    public String getOtherPartyRep() {
        return otherPartyRep;
    }

    public void setOtherPartyRep(String otherPartyRep) {
        this.otherPartyRep = otherPartyRep;
    }


    public List<Precinct> getPrecincts() {
        return precincts;
    }

    public void setPrecincts(List<Precinct> precincts) {
        this.precincts = precincts;
    }


    public List<Precinct> getBorderPrecincts() {
        return borderPrecincts;
    }

    public void setBorderPrecincts(List borderPrecincts){
        this.borderPrecincts = borderPrecincts;
    }

    public void setBorderPrecincts() {
        borderPrecincts = new ArrayList<Precinct>();
        for (Precinct precinct : precincts) {
            if (calculateBordering(precinct))
                borderPrecincts.add(precinct);
        }
    }

    public boolean calculateBordering(Precinct precinct) {
        for (Precinct adjacentPrecinct : precinct.getAdjacentPrecincts()) {
            if (!adjacentPrecinct.getDistrictName().equals(precinct.getDistrictName())) {
                return true;
            }
        }
        return false;
    }

    public void removePrecinct(String id) {
        int counter = 0;
        for (int i = 0; i < precincts.size(); i++) {
            if (precincts.get(i).getId().equals(id)) {
                counter = i;
            }
        }
        precincts.remove(counter);
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

    public boolean isContiguity() {
        return contiguity;
    }

    public void setContiguity(boolean contiguity) {
        this.contiguity = contiguity;
    }

    public boolean isPreservation() {
        return preservation;
    }

    public void setPreservation(boolean preservation) {
        this.preservation = preservation;
    }

    public double getArea() {
        ArrayList<Geometry> geometries = new ArrayList<>();
        for (Precinct precinct : precincts) {
            ArrayList<Coordinate> coords = new ArrayList<>();
            for (double[] coord : precinct.getPoints()) {
                Coordinate position = new Coordinate(coord[0], coord[1]);
                coords.add(position);
            }
            Coordinate firstPosition = new Coordinate(precinct.getPoints().get(0)[0], precinct.getPoints().get(0)[1]);
            coords.add(firstPosition);
            Coordinate[] coordinates = coords.toArray(new Coordinate[0]);
            Geometry precinctGeometry = new GeometryFactory().createPolygon(coordinates);
            geometries.add(precinctGeometry);
        }
        GeometryFactory geometryFactory = new GeometryFactory();
        Geometry districtGeometry = geometryFactory.buildGeometry(geometries);
        return districtGeometry.getArea();
    }

    public double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    }

    public double getBoundingCircle() {
        double minX = 360, maxX = -360, minY = 360, maxY = -360;
        for (Precinct precinct : precincts) {
            for (double[] point : precinct.getPoints()) {
                if (minX == 360) {
                    minX = point[0];
                } else if (minX > point[0]) {
                    minX = point[0];
                }
                if (maxX == -360) {
                    maxX = point[0];
                } else if (maxX < point[0]) {
                    maxX = point[0];
                }
                if (minY == 360) {
                    minY = point[1];
                } else if (minY > point[1]) {
                    minY = point[1];
                }
                if (maxY == -360) {
                    maxY = point[1];
                } else if (maxY < point[1]) {
                    maxY = point[1];
                }
            }
        }
        double midpointX = (maxX + minX) / 2;
        double midpointY = (maxY + minY) / 2;
        double radius = 0;
        for (Precinct precinct : precincts) {
            for (double[] point : precinct.getPoints()) {
                double length = distance(midpointX, midpointY, point[0], point[1]);
                if (length > radius) {
                    radius = length;
                }
            }
        }
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        ArrayList<Geometry> geometries = new ArrayList<>();
        for (Precinct precinct : precincts) {
            ArrayList<Coordinate> coords = new ArrayList<>();
            for (double[] coord : precinct.getPoints()) {
                Coordinate position = new Coordinate(coord[0], coord[1]);
                coords.add(position);
            }
            Coordinate firstPosition = new Coordinate(precinct.getPoints().get(0)[0], precinct.getPoints().get(0)[1]);
            coords.add(firstPosition);
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
            Coordinate[] coordinates = coords.toArray(new Coordinate[0]);
            LinearRing ring = geometryFactory.createLinearRing(coordinates);
            LinearRing holes[] = null;
            Polygon polygon = geometryFactory.createPolygon(ring, holes);
            Geometry precinctGeometry = polygon.getBoundary();
            geometries.add(precinctGeometry);
        }
        Geometry[] geoms = geometries.toArray(new Geometry[0]);
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
        GeometryCollection geometryCollection = geometryFactory.createGeometryCollection(geoms);
        Geometry districtGeometry = geometryCollection.union();
        AreaFunction areaFunction = new AreaFunction();
        double districtPerimeter = areaFunction.getPerimeter(districtGeometry);
        return districtPerimeter;
    }

    public double convexHullArea() {
        ArrayList<Geometry> geometries = new ArrayList<>();
        for (Precinct precinct : precincts) {
            ArrayList<Coordinate> coords = new ArrayList<>();
            for (double[] coord : precinct.getPoints()) {
                Coordinate position = new Coordinate(coord[0], coord[1]);
                coords.add(position);
            }
            Coordinate firstPosition = new Coordinate(precinct.getPoints().get(0)[0], precinct.getPoints().get(0)[1]);
            coords.add(firstPosition);
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
            Coordinate[] coordinates = coords.toArray(new Coordinate[0]);
            LinearRing ring = geometryFactory.createLinearRing(coordinates);
            LinearRing holes[] = null;
            Polygon polygon = geometryFactory.createPolygon(ring, holes);
            Geometry precinctGeometry = polygon.getBoundary();
            geometries.add(precinctGeometry);
        }
        GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory();
        Geometry districtGeometry = geometryFactory.buildGeometry(geometries);
        Geometry convexHull = districtGeometry.convexHull();
        AreaFunction areaFunction = new AreaFunction();
        return areaFunction.getArea(convexHull);
    }

    public int getNumPoints() {
        int counter = 0;
        for (Precinct precinct : precincts) {
            counter += precinct.getPoints().size();
        }
        return counter;
    }

    public void setXY() {

        X = new double[getNumPoints()];
        Y = new double[getNumPoints()];
        int counter = 0;
        for (Precinct precinct : precincts) {
            for (double[] point : precinct.getPoints()) {
                X[counter] = point[0];
                Y[counter] = point[1];
                counter++;
            }
        }
    }

    public double[] getX() {
        return X;
    }

    public void setX(double[] x) {
        X = x;
    }

    public double[] getY() {
        return Y;
    }

    public void setY(double[] y) {
        Y = y;
    }

    public double getAreaConvexHull() {
        return areaConvexHull;
    }

    public void setAreaConvexHull(double areaConvexHull) {
        this.areaConvexHull = areaConvexHull;
    }

    @Override
    public District clone(){
        District clonedDistrict = new District(this);
        ArrayList<Precinct> clonedPrecincts = new ArrayList<>();
        for (Precinct precinct : precincts){
            clonedPrecincts.add(precinct.clone());
        }
        ArrayList<Precinct> clonedBorderPrecincts = new ArrayList<>();
        for (Precinct borderPrecinct : borderPrecincts){
            clonedBorderPrecincts.add(borderPrecinct.clone());
        }
        clonedDistrict.setPrecincts(clonedPrecincts);
        clonedDistrict.setBorderPrecincts(clonedBorderPrecincts);
        return clonedDistrict;
    }
}