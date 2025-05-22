package mock_exam.models;
/*
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	city TEXT,
	country TEXT,
	latitude REAL,
	longitude REAL*/

import java.util.Objects;

public class City {
    private int id;
    private String city;
    private String country;
    private double latitude;
    private double longitude;

    //para los select
    public City(int id, String city, String country,
                double latitude, double longitude) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    //para insert
    public City(String city, String country, double latitude, double longitude) {
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    /*public void setLatitude(double latitude) {
        this.latitude = latitude;
    }*/

    public double getLongitude() {
        return longitude;
    }

    /*public void setLongitude(double longitude) {
        this.longitude = longitude;
    }*/

    @Override
    public String toString() {
        return String.format("%d,%s,%S,%.4f,%.4f", id, city, country, latitude, longitude);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return id == city.id && Double.compare(latitude, city.latitude) == 0 && Double.compare(longitude, city.longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude);
    }
}
