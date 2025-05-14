package ejercicio;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        //probar que la conexión es singleton
        Connection connection = null;
        for (int i = 0; i < 10; i++) {
            connection = Conexion.getInstance().getConnection();
            System.out.println(connection);
        }
        connection = Conexion.getInstance().getConnection();
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
