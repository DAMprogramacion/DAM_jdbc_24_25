package teoria.patronDAO;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//igual que en el ejemplo1, pero usando el patrón Singleton
public class ConnectionDB {
    private static ConnectionDB conexionSingleton;
    private Connection connection;
    private ConnectionDB() throws SQLException {
        String DB_URL = "jdbc:sqlite:databases/ejemplo.db";
        SQLiteConfig sqLiteConfig = new SQLiteConfig();
        sqLiteConfig.enforceForeignKeys(true);
        connection = DriverManager.getConnection(DB_URL, sqLiteConfig.toProperties());
    }
    public static ConnectionDB getInstance() throws SQLException {
        if (conexionSingleton == null)
            conexionSingleton = new ConnectionDB();
        return conexionSingleton;
    }

    public Connection getConnection() {
        return connection;
    }
    public void cerrarConexion() throws SQLException {
        if (connection != null)
            connection.close();
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(ConnectionDB.getInstance().getConnection());
        System.out.println(ConnectionDB.getInstance().getConnection());
        System.out.println(ConnectionDB.getInstance().getConnection());
    }
}
