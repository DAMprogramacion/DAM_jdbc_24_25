package teoria.patronDAO.daoUsuarios;

import teoria.introduccion.accesoBD.PatronUnico;
import teoria.patronDAO.ConnectionDB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public boolean insertarUsuario(Usuario usuario) {

        return false;
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
        return null;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public boolean borrarUsuarioPorDNI(String dni) {
        return false;
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
