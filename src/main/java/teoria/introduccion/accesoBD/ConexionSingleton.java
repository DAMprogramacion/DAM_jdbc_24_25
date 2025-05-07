package teoria.introduccion.accesoBD;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//igual que en el ejemplo1, pero usando el patrón Singleton
public class ConexionSingleton {
    private static ConexionSingleton conexionSingleton;
    private Connection connection;
    private ConexionSingleton() throws SQLException {
        String DB_URL = "jdbc:sqlite:databases/ejemplo.db";
        SQLiteConfig sqLiteConfig = new SQLiteConfig();
        sqLiteConfig.enforceForeignKeys(true);
        connection = DriverManager.getConnection(DB_URL, sqLiteConfig.toProperties());
    }
    public static ConexionSingleton getInstance() throws SQLException {
        if (conexionSingleton == null)
            conexionSingleton = new ConexionSingleton();
        return conexionSingleton;
    }

    public Connection getConnection() {
        return connection;
    }
    public void cerrarConexion() throws SQLException {
        if (connection != null)
            connection.close();
    }
}
