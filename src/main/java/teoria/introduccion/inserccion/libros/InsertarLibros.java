package teoria.introduccion.inserccion.libros;

import teoria.introduccion.accesoBD.ConexionSingleton;
import teoria.introduccion.clases.Libro;
import teoria.introduccion.cosultas.categoria.ConsultasTablaCategoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class InsertarLibros {
    private Connection connection;

    public InsertarLibros() {
        try {
            connection = ConexionSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean insertarLibro (Libro libro, int idCategoria) {
        //comprobar que existe la categoria
        ConsultasTablaCategoria tablaCategoria = new ConsultasTablaCategoria();
        String categoria = tablaCategoria.getNombreCategoriaByIdMejorado(idCategoria);
        if (categoria.length() == 0)
            return false;
        int filasAfectadas = 0;
        String sql = "INSERT INTO libro ( nombre, autor, editorial, idCategoria) VALUES ( ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getNombreLibro() );
            statement.setString(2, libro.getAutor() );
            statement.setString(3, libro.getEditorial() );
            statement.setInt(4, idCategoria);
            filasAfectadas = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filasAfectadas == 1;
    }

    public int insertarMuchosLibros (Map<Libro, Integer> libros) {
        int contador = 0;
        //recorrer el Map y cambiar el contador
        Set<Libro> keys = libros.keySet();
        for (Libro key : keys) {
            if (insertarLibro(key, libros.get(key)))
                contador++;
        }
        return contador;
    }







}
