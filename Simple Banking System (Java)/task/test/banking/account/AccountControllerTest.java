package banking.account;

import banking.database.CardDAO;
import banking.utility.database.DatabaseTestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class AccountControllerTest {
    private Account testAccount;
    private CardDAO dao;
    private String databaseName;
    private Connection conn;

    /**
     * Prepares a fresh test database before each test run.
     * Ensures the table exists and is emptied, then adds one test account.
     */
    @Before
    public void setUp() {
        this.databaseName = "testAccountController.s3db";
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
        this.dao = new CardDAO(conn);
        dao.ensureCardTableExists();
        dao.clearAllCards();

        testAccount = new Account();
        dao.addCard(testAccount.getCardNumber(), testAccount.getPin(), testAccount.getBalance());
    }

    /**
     * Removes the database used for testing
     */
    @After
    public void tearDown() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
        DatabaseTestUtils.deleteDatabaseFile(databaseName);
    }

    /**
     * Verifies that only one account exists, the one created in the before method.
     */
    @Test
    public void testCreateAccountOption() {
        assertEquals("Database should contain exactly 1 account after setup",
                1,
                dao.countAllCards());
    }

}