package ejercicio.DAOs;

import ejercicio.Conexion;
import ejercicio.modelos.Pelicula;

import java.sql.Connection;

public class PeliculaDAOImpl implements PeliuculaDAO{
    private Connection conn = Conexion.getInstance().getConnection();
    @Override
    public void insertarPelicula(Pelicula pelicula) {
        String sql1 = " INSERT INTO video VALUES (?, ?, ?, ?, ?, ?);";
        String sql2 = " INSERT INTO pelicula VALUES (?, ?, ?);";
    }
}
