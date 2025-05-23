package mock_exam;

import mock_exam.dao.CityDAO;
import mock_exam.dao.CityDAOImpl;
import mock_exam.models.City;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CityDAO dao = new CityDAOImpl();
        City newCity = new City("Jaén", "Spain", 37.76922, -3.79028);
        City city =dao.insertCity(newCity);
        if (city == null)
            System.out.printf("%s of %s exists%n", newCity.getCity(), newCity.getCountry());
        else
            System.out.println("Sucessful insert");
        newCity = new City("Córdoba", "Spain", 37.42, 4.47 );
        city =dao.insertCity(newCity);
        if (city == null)
            System.out.printf("%s of %s exists%n", newCity.getCity(), newCity.getCountry());
        else
            System.out.println("Sucessful insert");
        List<City> cities = dao.getCities();
        System.out.printf("Number of cities: %d%n", cities.size());
        Helper.createBackup(cities);
        List<City> citiesSpain = dao.getCitiesByCountry("Spain");
        citiesSpain.forEach(System.out::println);
        Helper.createBackupByCountry(citiesSpain);
        /*List<City> cities1 = Helper.readCities1("files/data.csv");
        for (City city1 : cities1)
            dao.insertCity(city1);*/
        List<City> cities1 = Helper.readCities2("files/data.csv");
        for (City city1 : cities1)
            dao.insertCity(city1);

    }
}
