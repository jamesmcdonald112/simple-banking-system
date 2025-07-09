package banking.database;

import banking.account.Account;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class CardDAOTest {

    /**
     * Ensures that the 'card' table exists in the database.
     * If it does not exist, it will be created.
     */
    @Test
    public void testCreateTable() {
        String dbName = "test.s3db";
        CardDAO dao = new CardDAO(dbName);
        dao.ensureCardTableExists();

        boolean exists = tableExists("card", dbName);
        assertTrue("Card table should exist",
                exists);
    }

    @Test
    public void testAddCard() {
        Account account = new Account();
        CardDAO dao = new CardDAO("testAddCard.s3db");
        dao.ensureCardTableExists();
        dao.addCard(account.getCardNumber(), account.getPin(), account.getBalance());

        Account retrievedAccount = dao.findByCardAndPin(account.getCardNumber(), account.getPin());
        assertNotNull("Card should be found in the database",
                retrievedAccount);

        assertEquals("Card numbers from account and retrieved account should match",
                account.getCardNumber(),
                retrievedAccount.getCardNumber());

        assertEquals("Pins from account and retrieved account should match",
                account.getPin(),
                retrievedAccount.getPin());
    }


    /**
     * Checks whether a given table exists in the specified SQLite database
     * using metadata from the database connection.
     *
     * @param tableName the name of the table to check
     * @param dbName the database file name
     * @return true if the table exists; false otherwise
     */
    private boolean tableExists(String tableName, String dbName) {
        String url = "jdbc:sqlite:" + dbName;
        try (Connection conn = DriverManager.getConnection(url);
             ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}