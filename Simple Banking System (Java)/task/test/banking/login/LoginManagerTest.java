package banking.login;

import banking.account.Account;
import banking.database.CardDAO;
import banking.utility.database.DatabaseTestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class LoginManagerTest {
    private Account testAccount;
    private CardDAO dao;
    private String databaseName;
    private Connection conn;

    /**
     * Sets up a fresh test environment before each test by:
     * - Clearing all accounts from the database
     * - Creating and adding a new test account
     */
    @Before
    public void setUp() {
        this.databaseName = "testAccountController.s3db";
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to test database", e);
        }

        this.dao = new CardDAO(conn);
        dao.ensureCardTableExists();
        dao.clearAllCards(); // Clean slate

        testAccount = new Account();
        dao.addCard(testAccount.getCardNumber(), testAccount.getPin(), testAccount.getBalance());
    }

    @After
    public void teardown() {
        DatabaseTestUtils.deleteDatabaseFile(databaseName);
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Verifies that a valid card number and PIN combination is recognised as a valid login
     */
    @Test
    public void testIsValidLogin() {
        boolean isValidLogin = LoginManager.isValidLogin(testAccount.getCardNumber(),
                testAccount.getPin(), dao);

        assertTrue("Login should succeed for a valid card and PIN",
                isValidLogin);
    }

    /**
     * Verifies that an invalid card number or PIN combination is recognised as an invalid login
     */
    @Test
    public void testIsInvalidLogin() {
        Account dummyAccount = new Account();

        // Invalid card number
        boolean isValidLogin = LoginManager.isValidLogin(dummyAccount.getCardNumber(),
                testAccount.getPin(), dao);
        assertFalse("Login should not succeed for an invalid card number",
                isValidLogin);

        // Invalid Pin
        isValidLogin = LoginManager.isValidLogin(testAccount.getCardNumber(),
                dummyAccount.getPin(), dao);
        assertFalse("Login should not succeed for an invalid PIN",
                isValidLogin);
    }

}