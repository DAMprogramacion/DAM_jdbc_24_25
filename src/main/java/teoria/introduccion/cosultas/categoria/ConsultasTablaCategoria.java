package teoria.introduccion.cosultas.categoria;

import teoria.introduccion.accesoBD.ConexionSingleton;
import teoria.introduccion.clases.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultasTablaCategoria {
    private Connection connection;

    public ConsultasTablaCategoria() {
        try {
            connection = ConexionSingleton.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Categoria> getCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM categoria ;";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id        = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                Categoria categoria = new Categoria(id, nombre);
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categorias;
    }
    public String getNombreCategoriaById (int id) {
        String categoria = "";
        String sql = "SELECT nombre FROM categoria WHERE id = " + id + ";";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
                categoria = resultSet.getString("nombre");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoria;
    }
    public String getNombreCategoriaByIdMejorado (int id) {
        String categoria = "";
        String sql = "SELECT nombre FROM categoria WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                categoria = resultSet.getString("nombre");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoria;
    }




}






