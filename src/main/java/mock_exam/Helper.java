package mock_exam;

import mock_exam.models.City;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Helper {
    //metodo que haga un backup, el backup se llama backup_2025_05_23.txt
    //guardamos en cada línea: Jaén: Spain
    public static void createBackup (List<City> cities) {
        if (cities.isEmpty())
            return;
        //usamos Files.writeString
        LocalDate dateNow = LocalDate.now();
        StringBuilder builder = new StringBuilder("backup_");
        builder.append(dateNow.getYear()).append('_');
        builder.append(dateNow.getMonthValue()).append('_');
        builder.append(dateNow.getDayOfMonth()).append(".txt");
        String namePath = builder.toString();
        StringBuilder builder1 = new StringBuilder();
        for (City city : cities) {
            builder1.append(city.getCity()).append(": ").append(city.getCountry()).append('\n');
        }
        String data = builder1.toString();
        Path path = Path.of("files/" + namePath);
        try {
            Files.writeString(path, data, StandardOpenOption.CREATE);
            System.out.printf("Created backup %s%n", namePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static  void createBackupByCountry(List<City> cities) {
        //nombre:  backup_Spain_2025_05_23.txt
        //contenido por línea: 1. Jaén
        if (cities.isEmpty())
            return;
        //usamos PrintWriter
        LocalDate dateNow = LocalDate.now();
        StringBuilder builder = new StringBuilder("backup_").
                append(cities.get(0).getCountry()).append('_');
        builder.append(dateNow.getYear()).append('_');
        builder.append(dateNow.getMonthValue()).append('_');
        builder.append(dateNow.getDayOfMonth()).append(".txt");
        String namePath = builder.toString();
        String fileName = "files/" + namePath;
        try (PrintWriter out = new PrintWriter(fileName)) {
            int count = 0;
            for (City city : cities) {
                out.printf("%d. %s%n", ++count, city.getCity());
                out.flush();
            }
            System.out.printf("Created backup %s%n", namePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static List<City> readCities1(String paht) {
        //Files.readAllLines
        List<City> cities = new ArrayList<>();
        Path path = Path.of(paht);
        try {
            List<String> lines = Files.readAllLines(path);
            //System.out.println("nº lineas léidas: " + lines.size());
            for (String line : lines) {
                String[] tokens = line.split(",");
                String city = tokens[0];
                String country = tokens[1];
                String sLatitude = tokens[2];
                double dLatitude = Double.parseDouble(sLatitude);
                String sLongitude = tokens[3];
                double dLongitude = Double.parseDouble(sLongitude);
                City city1 = new City(city, country, dLatitude, dLongitude);
                cities.add(city1);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
    public static List<City> readCities2(String paht) {
        List<City> cities = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(paht))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                String city = tokens[0];
                String country = tokens[1];
                String sLatitude = tokens[2];
                double dLatitude = Double.parseDouble(sLatitude);
                String sLongitude = tokens[3];
                double dLongitude = Double.parseDouble(sLongitude);
                City city1 = new City(city, country, dLatitude, dLongitude);
                cities.add(city1);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    public static void main(String[] args) {
        List<City> cities1 = Helper.readCities1("files/data.csv");

    }













}





