package ejercicio.DAOs;

import ejercicio.Conexion;
import ejercicio.Helper;
import ejercicio.excepciones.ValoracionException;
import ejercicio.modelos.Pelicula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeliculaDAOImpl implements PeliuculaDAO {
    private Connection conn = Conexion.getInstance().getConnection();

    @Override
    public void insertarPelicula(Pelicula pelicula) {
      //  String id = Helper.generarUUID();
        String sql1 = " INSERT INTO video VALUES (?, ?, ?, ?, ?, ?);";
        String sql2 = " INSERT INTO pelicula VALUES (?, ?, ?);";
        PreparedStatement statement = null;
        int rows1 = 0, rows2 = 0;
        try {
            conn.setAutoCommit(false); //inicio de transacción
            statement = conn.prepareStatement(sql1);
            statement.setString(1, pelicula.getIdVideo());
            statement.setString(2, pelicula.getTitulo());
            statement.setString(3, pelicula.getSinopsis());
            statement.setString(4, pelicula.getImagen());
            statement.setString(5, pelicula.getDescarga());
            statement.setFloat(6, pelicula.getDuracion());
            rows1 = statement.executeUpdate();
            statement = conn.prepareStatement(sql2);
            statement.setString(1, pelicula.getIdVideo());
            statement.setString(2, pelicula.getDirector());
            statement.setInt(3, pelicula.getValoracion());
            rows2 = statement.executeUpdate();
            conn.commit(); //fin de transacción
            System.err.println("Insertada película");
        } catch (SQLException e) {
            try {
                System.err.println("Error insertado película");
                conn.rollback();  //vuelta hacia atrás, fallo en alguna sentencia
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            ;
        }
    }

    @Override
    public boolean borrarPeliculaPorId(String id) {
        String sql = "delete FROM video WHERE idVideo = ?;";
        int rows = 0;
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, id);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows == 1;
    }

    @Override
    public Pelicula obtenerPeliculaPorId(String id) {
        String sql = "select * from vista_peliculas WHERE video_id = ?;";
        Pelicula pelicula = null;
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
             statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String titulo      = resultSet.getString(2);
                String sinopsis    = resultSet.getString(3);
                String imagen      = resultSet.getString(4);
                String descarga    = resultSet.getString(5);
                float  duracion    = resultSet.getFloat(6);
                String director    = resultSet.getString(7);
                int    valoracion  = resultSet.getInt(8);
                pelicula = new Pelicula(id, titulo, sinopsis, imagen, descarga, duracion,
                        director, valoracion);

            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pelicula;
    }

    @Override
    public List<Pelicula> obtenerTodasPeliculas() {
        String sql = "select * from vista_peliculas;";
        List<Pelicula> peliculas = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String id          = resultSet.getString(1);
                String titulo      = resultSet.getString(2);
                String sinopsis    = resultSet.getString(3);
                String imagen      = resultSet.getString(4);
                String descarga    = resultSet.getString(5);
                float  duracion    = resultSet.getFloat(6);
                String director    = resultSet.getString(7);
                int    valoracion  = resultSet.getInt(8);
                Pelicula pelicula = new Pelicula(id, titulo, sinopsis, imagen, descarga, duracion,
                        director, valoracion);
                peliculas.add(pelicula);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peliculas;
    }

    @Override
    public List<Pelicula> obtenerPeliculasSegunValoracion(int valoracion) {
        String sql = " select * from vista_peliculas where valoracion = ?;";
        List<Pelicula> peliculas = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, valoracion);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String id          = resultSet.getString(1);
                String titulo      = resultSet.getString(2);
                String sinopsis    = resultSet.getString(3);
                String imagen      = resultSet.getString(4);
                String descarga    = resultSet.getString(5);
                float  duracion    = resultSet.getFloat(6);
                String director    = resultSet.getString(7);
               // int    valoracion  = resultSet.getInt(8);
                Pelicula pelicula = new Pelicula(id, titulo, sinopsis, imagen, descarga, duracion,
                        director, valoracion);
                peliculas.add(pelicula);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peliculas;
    }

    @Override
    public List<Pelicula> obtenerTodasPeliculasOrdenadasPorValoracion() {
        String sql = "select * from vista_peliculas ORDER BY valoracion DESC;";
        List<Pelicula> peliculas = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String id          = resultSet.getString(1);
                String titulo      = resultSet.getString(2);
                String sinopsis    = resultSet.getString(3);
                String imagen      = resultSet.getString(4);
                String descarga    = resultSet.getString(5);
                float  duracion    = resultSet.getFloat(6);
                String director    = resultSet.getString(7);
                int    valoracion  = resultSet.getInt(8);
                Pelicula pelicula = new Pelicula(id, titulo, sinopsis, imagen, descarga, duracion,
                        director, valoracion);
                peliculas.add(pelicula);
            }
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return peliculas;
    }

    @Override
    public Pelicula actualizarValoracionPelicula(String idPelicula, int nuevaValoracion) {
        String sql = " UPDATE pelicula SET valoracion = ? WHERE idPelicula = ?;";
        int rows = 0;
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, nuevaValoracion);
            statement.setString(2, idPelicula);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (rows == 0)
            return null;
        return obtenerPeliculaPorId(idPelicula);
    }


    public static void main(String[] args) throws ValoracionException {
        Pelicula newPelicula = new Pelicula( "Capitan America", "Superheroes",
                "/home/imagenes/cAmerica.png","/home/descarga/cAmerica.avi",
                200, "John Carpenter", 4);
        PeliuculaDAO dao = new PeliculaDAOImpl();
        dao.insertarPelicula(newPelicula);
        //boolean exito = dao.borrarPeliculaPorId("10");
        //System.out.printf("Borrada película? %B%n", exito);
       // Pelicula pelicula = dao.obtenerPeliculaPorId("11");
        //System.out.println(pelicula);
       // List<Pelicula> peliculas = dao.obtenerTodasPeliculas();
        //peliculas.forEach(System.out::println);
        List<Pelicula> peliculas = dao.obtenerPeliculasSegunValoracion(3);
        peliculas.forEach(System.out::println);
    }
}
