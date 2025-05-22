package mock_exam.dao;

import mock_exam.models.City;

public interface CityDAO {
    //crud
    //insertar una ciudad
    City insertCity(City city);
    //devolver todas las ciudades
    //devolver una ciudad por id
    //devolver ciudades de un pais
    //devolver ciudades, parametros (norte, sur)
    //actualizar el nombre de la ciudad
    //borrar la ciudad por id
    //borrar todas las ciudades de un pais
}
