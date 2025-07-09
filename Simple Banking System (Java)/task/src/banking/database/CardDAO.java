package banking.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CardDAO {
    private final String url;

    /**
     * Constructs a CardDAO with a JDBC URL pointing to the specified SQLite databse.
     *
     * @param databaseName the name of the database file as a String
     */
    public CardDAO(String databaseName) {
        this.url = "jdbc:sqlite:" + databaseName;
    }

    /**
     * Ensures that the 'card' table exists in the database.
     * If it does not exist, it will be created.
     */
    public void ensureCardTableExists() {
        String createTable = """
            CREATE TABLE IF NOT EXISTS card (
                id INTEGER PRIMARY KEY,
                number TEXT,
                pin TEXT,
                balance INTEGER DEFAULT 0
            );
            """;

        try (Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            stmt.execute(createTable);
        } catch (SQLException e) {
            System.err.println("Failed to create table: " + e.getMessage());
        }

    }
}
