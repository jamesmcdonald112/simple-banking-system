package banking.database;

import banking.account.Account;

import java.sql.*;

public class CardDAO {
    private final String url;

    /**
     * Constructs a CardDAO with a JDBC URL pointing to the specified SQLite database.
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

    /**
     * Adds a card number, PIN, and balance to the card table in the database.
     *
     * @param cardNumber card number as a String
     * @param pin PIN as a String
     * @param balance Balance as an int
     */
    public void addCard(String cardNumber, String pin, int balance) {
        String sql = """
                INSERT INTO card (number, pin, balance)
                VALUES (?,?,?)""";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cardNumber);
            stmt.setString(2, pin);
            stmt.setInt(3, balance);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Something went wrong adding the card to the database: " + e.getMessage());
        }
    }

    /**
     * Searches the database by card and PIN and returns an account.
     *
     * @param cardNumber card number as a String
     * @param pin PIN as a String
     * @return The Account as an Account if found; null otherwise.
     */
    public Account findByCardAndPin(String cardNumber, String pin) {
        String sql = """
                SELECT number, pin, balance 
                FROM card
                WHERE number = ? AND
                pin = ?""";
        try(Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cardNumber);
            stmt.setString(2, pin);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getString("number"),
                        rs.getString("pin"),
                        rs.getInt("balance")
                );
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong finding the card by card number and pin in " +
                               "the database: " + e.getMessage());

        }
        return null;
    }
}
