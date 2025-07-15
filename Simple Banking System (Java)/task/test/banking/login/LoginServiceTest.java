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

public class LoginServiceTest {
    private Account testAccount;
    private LoginService loginService;
    private CardDAO dao;
    private Connection conn;

    /**
     * Clears the AccountStore of any accounts before each test
     */
    @Before
    public void setUp() {

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:testLoginService.s3db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dao = new CardDAO(conn);
        dao.ensureCardTableExists();
        dao.clearAllCards();
        testAccount = new Account();
        dao.addCard(testAccount.getCardNumber(), testAccount.getPin(), testAccount.getBalance());
        loginService = new LoginService(); // Assuming this is a utility class with static methods, or else instantiate appropriately
    }

    @After
    public void tearDown() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
        DatabaseTestUtils.deleteDatabaseFile("testLoginService.s3db");    }

    /**
     * Verifies that a valid login attempt succeeds.
     */
    @Test
    public void testHandleLoginOptionSuccess() {
        boolean isValidLogin = loginService.handleLoginOption(testAccount.getCardNumber(),
                testAccount.getPin(), dao);

        assertTrue("Login should succeed with a valid card number and PIN",
                isValidLogin);
    }

    /**
     * Creates a dummy account for incorrect credentials.
     * Verifies that an invalid login attempt fails.
     */
    @Test
    public void testHandleLoginOptionFailure() {
        // Dummy account (used only to get incorrect credentials)
        Account dummyAccount = new Account();
        String dummyCardNumber = dummyAccount.getCardNumber();
        String dummyPin = dummyAccount.getPin();

        // Incorrect card number
        boolean resultWrongCard = loginService.handleLoginOption(dummyCardNumber, testAccount.getPin(), dao);
        assertFalse("Login should not succeed with an invalid card number",
                resultWrongCard);

        // Incorrect PIN
        boolean resultWrongPin = loginService.handleLoginOption(testAccount.getCardNumber(), dummyPin, dao);
        assertFalse("Login should not succeed with an invalid PIN",
                resultWrongPin);
    }

}