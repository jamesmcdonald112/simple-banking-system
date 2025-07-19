package banking.database;

import banking.account.Account;

import java.sql.*;

public class CardDAO {
    private final Connection conn;


    public CardDAO(Connection conn) {
        this.conn = conn;
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

        try (Statement stmt = conn.createStatement()) {
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
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cardNumber);
            stmt.setString(2, pin);
            stmt.setInt(3, balance);

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Something went wrong adding the card to the database: " + e.getMessage());
        }
    }

    /**
     * Counts all card entries in the database.
     *
     * @return Total cards as an int
     */
    public int countAllCards() {
        String sql = "SELECT COUNT(*) FROM card";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {


            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.err.println("Error counting all card in the database: " + e.getMessage());
        }
        return 0;
    }

    /**
     * Searches the database by card number and returns an account.
     *
     * @param cardNumber card number as a String
     * @return The Account as an Account if found; null otherwise.
     */
    public Account findByCard(String cardNumber) {
        String sql = """
                SELECT number, pin, balance
                FROM card
                WHERE number = ?
                """;
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cardNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getString("number"),
                            rs.getString("pin"),
                            rs.getInt("balance")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong finding the card by card number in the " +
                    "database: " + e.getMessage());
        }
        return null;
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
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cardNumber);
            stmt.setString(2, pin);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                            rs.getString("number"),
                            rs.getString("pin"),
                            rs.getInt("balance")
                    );
                }
            }


        } catch (SQLException e) {
            System.err.println("Something went wrong finding the card by card number and pin in " +
                               "the database: " + e.getMessage());

        }
        return null;
    }

    /**
     * Retrieves the balance for the given card number from the database.
     *
     * @param cardNumber the card number to search for
     * @return the account balance if found; -1 if not found or an error occurs
     */
    public int getBalanceByCardNumber(String cardNumber) {
        String balanceQuery = """
                SELECT balance 
                FROM card
                WHERE number = ?;
                """;
        try(PreparedStatement stmt = conn.prepareStatement(balanceQuery)) {
            stmt.setString(1, cardNumber);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("balance");
                }
            }
        } catch (SQLException e) {
            System.err.println("Something went wrong fetching the balance from the database: " + e.getMessage());
        }
        return -1;
    }

    /**
     * Adds income to the balance of the card in the database.
     *
     * @param cardNumber the card number to search for
     * @param income the amount to add
     */
    public void addIncome(String cardNumber, int income) {
        String incomeUpdate = """
                UPDATE card
                SET balance = balance + ?
                WHERE number = ?
        """;

        try (PreparedStatement stmt = conn.prepareStatement(incomeUpdate)) {
            stmt.setInt(1, income);
            stmt.setString(2, cardNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Something went wrong updating the balance from the database: " + e.getMessage());
        }
    }


    /**
     * Clears all entries in the card database.
     */
    public void clearAllCards() {
        String sql = """
                DELETE FROM card
                """;

        try (Statement stmt = conn.createStatement()){

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Something went wrong clearing all cards from the database: " + e.getMessage());
        }
    }
}
