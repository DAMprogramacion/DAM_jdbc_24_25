package teoria.introduccion.cosultas.vistas;

import teoria.introduccion.accesoBD.ConexionSingleton;
import teoria.introduccion.clases.Categoria;
import teoria.introduccion.clases.LibroCategoria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultaLibroCategoria {
    private Connection connection;

    public ConsultaLibroCategoria() {
        try {
            connection = ConexionSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<LibroCategoria> getLibroCategorias() {
        List<LibroCategoria> libroCategorias = new ArrayList<>();
        String sql = "SELECT * FROM libro_categoria ;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String libro     = resultSet.getString("libro");
                String autor     = resultSet.getString("autor");
                String editorial = resultSet.getString("editorial");
                String categoria = resultSet.getString("categoria");
                LibroCategoria libroCategoria = new LibroCategoria(libro, autor, editorial, categoria);
                libroCategorias.add(libroCategoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libroCategorias;
    }
    public List<LibroCategoria> getLibrosCategoriaByCategoria (String categoriaBusqueda) {
        List<LibroCategoria> libroCategorias = new ArrayList<>();
        String sql = "SELECT * FROM libro_categoria WHERE categoria = '" + categoriaBusqueda + "';";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String libro     = resultSet.getString("libro");
                String autor     = resultSet.getString("autor");
                String editorial = resultSet.getString("editorial");
                String categoria = resultSet.getString("categoria");
                LibroCategoria libroCategoria = new LibroCategoria(libro, autor, editorial, categoria);
                libroCategorias.add(libroCategoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libroCategorias;
    }
}
