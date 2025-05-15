package ejercicio.DAOs;

import ejercicio.Conexion;
import ejercicio.Helper;
import ejercicio.excepciones.ValoracionException;
import ejercicio.modelos.Pelicula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public List<Pelicula> obtenerTodasPeliculas() {
        return List.of();
    }

    @Override
    public List<Pelicula> obtenerPeliculasSegunValoracion(int valoracion) {
        return List.of();
    }

    @Override
    public List<Pelicula> obtenerTodasPeliculasOrdenadasPorValoracion() {
        return List.of();
    }


    public static void main(String[] args) throws ValoracionException {
        Pelicula pelicula = new Pelicula( "1", "1", "1",
                "1", 200, "1", 1);
        PeliuculaDAO dao = new PeliculaDAOImpl();
        //dao.insertarPelicula(pelicula);
        boolean exito = dao.borrarPeliculaPorId("10");
        System.out.printf("Borrada película? %B%n", exito);
    }
}
