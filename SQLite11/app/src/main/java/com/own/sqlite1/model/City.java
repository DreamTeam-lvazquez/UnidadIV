package com.own.sqlite1.model;

/**
 * Created by vazquez on 08/04/2018.
 */

public class City
{
    private String idCity;
    private String nameCity;
    private String location;
    private String country;
    private int codePostal;
    private int numberInhabitants;
    private String type;

    public City(String idCity, String nameCity, String location, String country, int codePostal, int numberInhabitants, String type) {
        this.idCity = idCity;
        this.nameCity = nameCity;
        this.location = location;
        this.country = country;
        this.codePostal = codePostal;
        this.numberInhabitants = numberInhabitants;
        this.type = type;
    }

    public City() { this("","","","",0,0,"");
    }

    public String getIdCity() {
        return idCity;
    }

    public void setIdCity(String idCity) {
        this.idCity = idCity;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country= country;
    }

    public int getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(int codePostal) {
        this.codePostal = codePostal;
    }

    public int getNumberInhabitants() {
        return numberInhabitants;
    }

    public void setNumberInhabitants(int numberInhabitants) {
        this.numberInhabitants = numberInhabitants;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "City{" +
                "idCity='" + idCity + '\'' +
                ", nameCity='" + nameCity + '\'' +
                ", location='" + location + '\'' +
                ", country='" + country + '\'' +
                ", codePostal=" + codePostal +
                ", numberInhabitants=" + numberInhabitants +
                ", type='" + type + '\'' +
                '}';
    }
}
