package teoria.introduccion.accesoBD;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = ConexionSingleton.getInstance().getConnection();
            System.out.println(connection);
            connection = ConexionSingleton.getInstance().getConnection();
            System.out.println(connection);
             connection = ConexionSingleton.getInstance().getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            System.err.println("Error al conectarse a la BD");
            //throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Cerrada la conexión");
                } catch (SQLException e) {
                    //throw new RuntimeException(e);
                    System.err.println("Error cerrando la conexión");
                }
            }
        }
    }
}
