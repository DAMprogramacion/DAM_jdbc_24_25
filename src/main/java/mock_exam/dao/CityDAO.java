package mock_exam.dao;

import mock_exam.models.City;

import java.util.List;

public interface CityDAO {
    //crud
    //insertar una ciudad
    City insertCity(City city);
    //devolver todas las ciudades
    List<City> getCities();
    //devolver ciudades de un pais
    List<City> getCitiesByCountry(String country);
    //devolver una ciudad por id

    //devolver ciudades, parametros (norte, sur)
    //actualizar el nombre de la ciudad
    //borrar la ciudad por id
    //borrar todas las ciudades de un pais
}
