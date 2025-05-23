package mock_exam.dao;

import mock_exam.ConnectionDB;
import mock_exam.models.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDAOImpl implements CityDAO{

    private Connection connection = ConnectionDB.getInstance().getConnection();

    @Override
    public City insertCity(City city) {
        //comprobas que no existe la ciudad, pues la clave primaria es un id autoincremetable
        if (existCity(city))
            return null;
        String sql = "insert into cities (city, country, latitude, longitude) values (?, ?, ?, ?);";
        int rows = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, city.getCity());
            statement.setString(2, city.getCountry());
            statement.setDouble(3, city.getLatitude());
            statement.setDouble(4, city.getLongitude());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (rows == 1)
            return city;
        return null;
    }

    @Override
    public List<City> getCities() {
        String sql = " SELECT * FROM cities;";
        List<City> cities = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                int id           = resultSet.getInt("id");
                String cityDB    = resultSet.getString("city");
                String country   = resultSet.getString("country");
                double latitude  = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                City city = new City(id, cityDB, country, latitude, longitude);
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    @Override
    public List<City> getCitiesByCountry(String country) {
        String sql = " SELECT * FROM cities WHERE country = ?;";
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, country);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id           = resultSet.getInt("id");
                String cityDB    = resultSet.getString("city");
             //   String country   = resultSet.getString("country");
                double latitude  = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                City city = new City(id, cityDB, country, latitude, longitude);
                cities.add(city);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    private boolean existCity (City city) {
        String sql = " SELECT * FROM cities WHERE city = ? AND country = ?;";
        City research = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, city.getCity());
            statement.setString(2, city.getCountry());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id           = resultSet.getInt("id");
                String cityDB    = resultSet.getString("city");
                String country   = resultSet.getString("country");
                double latitude  = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");
                research = new City(id, cityDB, country, latitude, longitude);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  research != null;
    }


}
