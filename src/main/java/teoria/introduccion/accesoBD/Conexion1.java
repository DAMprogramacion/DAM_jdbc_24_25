package teoria.introduccion.accesoBD;
//ejemplo básico de conexión, hay foreing keys
import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion1 {
    public static final String DB_URL = "jdbc:sqlite:databases/ejemplo.db";
    public static void main(String[] args) {
        Connection connection = null;
        try {
            SQLiteConfig sqLiteConfig = new SQLiteConfig();
            sqLiteConfig.enforceForeignKeys(true);
             connection = DriverManager.getConnection(DB_URL, sqLiteConfig.toProperties());
            System.out.println("Conectado a la BD....");
            System.out.println(connection);
            connection = DriverManager.getConnection(DB_URL, sqLiteConfig.toProperties());
            System.out.println("Conectado a la BD....");
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Cerrada la BD");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
