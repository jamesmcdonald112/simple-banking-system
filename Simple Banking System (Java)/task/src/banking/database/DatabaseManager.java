package banking.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    /**
     * Connects to a SQLite database. If the database file does not exist, it will be created.
     * Uses a 5-second timeout to validate the connection.
     *
     * @param dbFileName the name of the SQLite database file
     * @return true if the connection is valid, false otherwise
     */
    public static boolean connectTo(String dbFileName) {
        String url = "jdbc:sqlite:" + dbFileName;
        try (Connection conn = DriverManager.getConnection(url)) {
            return conn.isValid(5);
        } catch (SQLException e) {
            System.err.println("DB connection error: " + e.getMessage());
            return false;
        }
    }
}
