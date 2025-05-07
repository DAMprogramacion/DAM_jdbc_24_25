package teoria.introduccion.accesoBD;

import org.sqlite.SQLiteConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
//igual que en el ejemplo1, pero usando un fichero propierties
public class Conexion2 {
    public static void main(String[] args) {

        Connection connection = null;
        try {
        Properties properties = new Properties();
        InputStream input = ClassLoader.getSystemClassLoader().
                getResourceAsStream("sqlite.propierties");
        properties.load(input);
        String driver = properties.getProperty("driver");
        String db     = properties.getProperty("db");
        String DB_URL = driver + db;
            SQLiteConfig sqLiteConfig = new SQLiteConfig();
            sqLiteConfig.enforceForeignKeys(true);
            connection = DriverManager.getConnection(DB_URL, sqLiteConfig.toProperties());
            System.out.println("Conectado a la BD....");
        } catch (SQLException | IOException e) {
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
