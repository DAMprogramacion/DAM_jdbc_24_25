package ejercicio;

import org.sqlite.SQLiteConfig;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {
    private static Conexion conexion;
    private Connection connection;

    private Conexion() {
        Properties properties = new Properties();
        InputStream input = ClassLoader.getSystemClassLoader().
                getResourceAsStream("db.propierties");
        try {
            properties.load(input);
            String driver = properties.getProperty("driver");
            String db     = properties.getProperty("db");
            String url = driver + db;
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);
            connection = DriverManager.getConnection(url, config.toProperties());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Conexion getInstance() {
        if (conexion == null)
            conexion = new Conexion();
        return conexion;
    }

    public Connection getConnection() {
        return connection;
    }
}
