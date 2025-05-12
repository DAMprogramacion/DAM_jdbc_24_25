package teoria.patronDAO.daoUsuarios;

import teoria.introduccion.accesoBD.PatronUnico;
import teoria.patronDAO.ConnectionDB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UsuarioDAOImpl implements usuarioDAO{
    //crear conexión
    private static Connection conexion;

    static {
        try {
            conexion = ConnectionDB.getInstance().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insertarUsuario(Usuario usuarioNew) {
        Usuario usuario = getUsuarioPorDNI(usuarioNew.getDni());
        if (usuario != null)
            return false;
        String sql = "INSERT INTO usuario VALUES (?, ?, ?)";
        int rows = 0;
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuarioNew.getDni());
            statement.setString(2, usuarioNew.getNombreUsuario());
            statement.setString(3, usuarioNew.getApellidos());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows == 1;
    }

    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario;";
        try (Statement statement = conexion.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next())
                usuarios.add(new Usuario(resultSet.getString("dni"),
                        resultSet.getString("nombre"), resultSet.getString("apellidos")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;  //////////
    }

    @Override
    public Usuario getUsuarioPorDNI(String dni) {
        String sql = " SELECT * FROM usuario WHERE dni = ?;";
        Usuario usuario = null;
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
                 usuario = new Usuario(resultSet.getString("dni"), resultSet.getString("nombre"),
                        resultSet.getString("apellidos"));
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    @Override
    public Usuario updateUsuario(Usuario usuarioNew) {
        String sql = "UPDATE usuario SET nombre = ?, apellidos = ? WHERE dni = ?;";
        int rows = 0;
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuarioNew.getNombreUsuario());
            statement.setString(2, usuarioNew.getApellidos());
            statement.setString(3, usuarioNew.getDni());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (rows != 1)
            return null;
        return usuarioNew;
    }

    @Override
    public boolean borrarUsuarioPorDNI(String dni) {
        /*Usuario usuario = getUsuarioPorDNI(dni);
        if (usuario != null)
            return false;*/
        String sql = " DELETE FROM usuario WHERE dni = ?";
        int rows = 0;
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, dni);
            rows = statement.executeUpdate();
            System.out.println("filas: " + rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rows == 1;
    }

    @Override
    public void grabarUsuarios(String sPath) {
        Path path = Path.of(sPath);
        List<Usuario> usuarios = getUsuarios();
        StringBuilder builder = new StringBuilder();
        for (Usuario usuario: usuarios)
            builder.append(usuario.toString()).append('\n');
        String datos = builder.toString();
        String datosLimpios = datos.substring(0, datos.length() -1);
        try {
            Files.writeString(path, datosLimpios,StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        List<Usuario> usuarios = usuarioDAO.getUsuarios();
        usuarios.forEach(System.out::println);
        usuarioDAO.grabarUsuarios("files/backup1.csv");

    }
}
