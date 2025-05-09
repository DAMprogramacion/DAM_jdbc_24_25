package teoria.introduccion.borradoYactualizacion.libros;

import teoria.introduccion.accesoBD.ConexionSingleton;
import teoria.introduccion.clases.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BorradoYActualizacionLibros {
    private Connection connection;

    public BorradoYActualizacionLibros() {
        try {
            connection = ConexionSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //metodo borrado dado el id, devuelve un boolean
    public boolean eliminarLibroPorId (int id) {
        String sql = " DELETE FROM libro WHERE id = ?;";
        int filasBorradas = 0;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            filasBorradas = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return filasBorradas == 1; //1 porque hacemos borrado por clave primaria
    }
    //metodo actualizamos un libro (nombre, autor, editorial) por id: devuelve el nuevo libro
    public Libro actualizarLibroPorId (Libro libro) {
        String sql = " UPDATE libro SET nombre = ?, autor = ?, editorial = ? WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, libro.getNombreLibro());
            statement.setString(2, libro.getAutor());
            statement.setString(3, libro.getEditorial());
            statement.setInt(4, libro.getIdLibro());
            int filasActualizdas =  statement.executeUpdate();
            if (filasActualizdas == 1)
                return libro;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}





