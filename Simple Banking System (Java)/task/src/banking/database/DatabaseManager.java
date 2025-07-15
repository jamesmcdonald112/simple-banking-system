package banking.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection conn;

    /**
     * Returns the active database connection.
     *
     * @return the current Connection object
     */
    public Connection getConnection() {
        return this.conn;
    }

    /**
     * Connects to a SQLite database. If the database file does not exist, it will be created.
     * Uses a 5-second timeout to validate the connection.
     *
     * @param dbFileName the name of the SQLite database file
     * @return true if the connection is valid, false otherwise
     */
    public boolean connectTo(String dbFileName) {
        String url = "jdbc:sqlite:" + dbFileName;
        try {
            this.conn = DriverManager.getConnection(url);
            return this.conn.isValid(5);
        } catch (SQLException e) {
            System.err.println("DB connection error: " + e.getMessage());
            return false;
        }
    }

    /**
     * Closes the active database connection if it is open.
     * Suppresses any SQLExceptions that occur during closing.
     */
    public void closeConnection() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
