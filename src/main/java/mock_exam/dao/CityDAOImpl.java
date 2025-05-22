package mock_exam.dao;

import mock_exam.ConnectionDB;
import mock_exam.models.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CityDAOImpl implements CityDAO{

    private Connection connection = ConnectionDB.getInstance().getConnection();

    @Override
    public City insertCity(City city) {
        //comprobas que no existe la ciudad, pues la clave primaria es un id autoincremetable
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
    private boolean existCity (City city) {

    }

    public static void main(String[] args) {
        CityDAO dao = new CityDAOImpl();
        City newCity = new City("Jaén", "Spain", 37.76922, -3.79028);
        City city =dao.insertCity(newCity);
    }
}
