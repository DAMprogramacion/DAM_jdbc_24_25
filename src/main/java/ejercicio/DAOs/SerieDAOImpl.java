package ejercicio.DAOs;

import ejercicio.Conexion;
import ejercicio.modelos.Capitulo;
import ejercicio.modelos.Serie;
import ejercicio.modelos.Temporada;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SerieDAOImpl implements SerieDAO{
    private Connection conexion;
    public SerieDAOImpl() {
        conexion = Conexion.getInstance().getConnection();
    }
    @Override
    public void crearSerie(Serie serie) {
        String sql1 = "INSERT INTO video VALUES (?, ?, ?, ?, ?, ?);";
        String sql2 = "INSERT INTO serie VALUES (?)";
        PreparedStatement statement = null;
        int rows1 = 0, rows2 = 0;
        //preparamos transacción
        try {
            conexion.setAutoCommit(false);
            //sentencias
            statement = conexion.prepareStatement(sql1);
            statement.setString(1, serie.getIdVideo());
            statement.setString(2, serie.getTitulo());
            statement.setString(3, serie.getSinopsis());
            statement.setString(4, serie.getImagen());
            statement.setString(5, serie.getDescarga());
            statement.setFloat (6, serie.getDuracion());
            rows1 = statement.executeUpdate();
            statement = conexion.prepareStatement(sql2);
            statement.setString(1, serie.getIdVideo());
            rows2 = statement.executeUpdate();
            conexion.commit();
        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } finally {
                System.err.println("Error en la insercción de una serie");
            }
            //throw new RuntimeException(e);

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Override
    public boolean borrarSerie(String id) {
        String sql = " delete FROM  serie WHERE idSerie = ?;";
        int rows = 0;
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, id);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows == 1;
    }

    @Override
    public List<Serie> obtenerTodasSeries() {
        List<Serie> series = new ArrayList<>();
        String sql = "SELECT idSerie, titulo, sinopsis, imagen, descarga, duracion FROM serie, video WHERE idSerie = idVideo ;";
        try (Statement statement = conexion.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String idSerie  = resultSet.getString("idSerie");
                String titulo   = resultSet.getString("titulo");
                String sinopsis = resultSet.getString("sinopsis");
                String imagen   = resultSet.getString("imagen");
                String descarga = resultSet.getString("descarga");
                float  duracion = resultSet.getFloat("duracion");
                Serie serie = new Serie(idSerie, titulo, sinopsis, imagen, descarga, duracion);
                series.add(serie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return series;
    }

    @Override
    public Serie obtenerSeriePorId(String id) {
        String sql = "SELECT * FROM video where idVideo = ?;";
        Serie serie = null;
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
               // String idSerie  = resultSet.getString("idVideo");
                String titulo   = resultSet.getString("titulo");
                String sinopsis = resultSet.getString("sinopsis");
                String imagen   = resultSet.getString("imagen");
                String descarga = resultSet.getString("descarga");
                float  duracion = resultSet.getFloat("duracion");
                serie = new Serie(id, titulo, sinopsis, imagen, descarga, duracion);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serie;
    }

    @Override
    public void crearTemporada(Temporada temporada) {
        //comprobar que existe la serie
        //comprobar que no existe la temporada-serie
        //sql = insert into temporada valuers(.......

    }

    @Override
    public void crearCapitulo(Capitulo capitulo) {

    }

    public static void main(String[] args) {
        SerieDAO dao = new SerieDAOImpl();
        /*Serie serie2 = new Serie("titulo serie 2", "sinsopsis serie 2",
                "/home/imagen/imagenSerie2", "/home/serie/descargaSerie2", 455);
        dao.crearSerie(serie2);
        boolean exito = dao.borrarSerie("25ac6cbd-376e-4f70-a6d3-6efe40cfddda");
        System.out.printf("Borrado de serie? %B%n", exito);
        List<Serie> series = dao.obtenerTodasSeries();
        series.forEach(System.out::println);*/
        Serie serie = dao.obtenerSeriePorId("f8af6fbc-a06e-4a64-ab5a-cb3c58a8fab7");
        System.out.println(serie);
    }
}
