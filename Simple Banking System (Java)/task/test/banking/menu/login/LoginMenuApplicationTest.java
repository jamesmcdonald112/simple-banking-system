package banking.menu.login;

import banking.account.Account;
import banking.database.CardDAO;
import banking.database.DatabaseConfig;
import banking.utility.database.DatabaseTestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LoginMenuApplicationTest {
    private Scanner scanner;
    private Account loggedInAccount;
    private boolean LoggedOut = false;
    private String databaseName;
    private Connection conn;
    private CardDAO cardDAO;

    /**
     * Creates a new account to test login
     */
    @Before
    public void setup() {
        this.databaseName = "testLoginMenuApplicationTest";
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            this.cardDAO = new CardDAO(conn);
        } catch (SQLException e) {
            System.err.println("DB connection error: " + e.getMessage());
        }
        cardDAO.ensureCardTableExists();
        cardDAO.clearAllCards();

        loggedInAccount = new Account();
        cardDAO.addCard(loggedInAccount.getCardNumber(), loggedInAccount.getPin(), loggedInAccount.getBalance());
    }

    /**
     * Closes the scanner after each test.
     */
    @After
    public void teardown() {
        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (SQLException e) {
            System.err.println("DB closing error: " + e.getMessage());
        }

        DatabaseTestUtils.deleteDatabaseFile(databaseName);
        if (scanner != null) scanner.close();
        System.setOut(System.out);
    }

    /**
     * Simulates user selecting "1" (Balance) followed by "0" (exit),
     * and verifies that the balance is printed to the screen.
     */
    @Test
    public void testViewBalanceThenExit() {
        String input = String.join("\n",
                "1", // Balance
                "0" // Exit
        ) + "\n";
        scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        LoginMenuApplication loginApp = new LoginMenuApplication(scanner, loggedInAccount, cardDAO);
        loginApp.start();

        String output = out.toString();

        assertTrue("Expected balance output to contain 'Balance: 0'",
                output.contains("Balance: 0"));

    }

    /**
     * Simulates user selecting "2" (Add income) followed by the amount "10000",
     * and verifies that the correct balance is printed to the screen.
     */
    @Test
    public void testHandleAddIncome() {
        String input = String.join("\n",
                "2", // Add income
                "10000", // income to add
                "1" // Balance
        ) + "\n";
        scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        LoginMenuApplication loginApp = new LoginMenuApplication(scanner, loggedInAccount, cardDAO);
        loginApp.start();

        String output = out.toString();

        assertTrue("Expected balance output to contain 'Balance: 10000",
                output.contains("Balance: 10000"));

        assertEquals("Expected balance in the card account is expected to be 10000",
                10000,
                cardDAO.getBalanceByCardNumber(loggedInAccount.getCardNumber()));
    }

    /**
     * Simulates user selecting Add income followed by the negative amount,
     * and verifies that the error message is printed to the screen.
     */
    @Test
    public void testAddIncome_negativeAmount_shouldNotUpdateBalance() {
        String input = String.join("\n",
                String.valueOf(LoginMenuResult.ADD_INCOME.getValue()), // Add income
                "-10000"
        );

        this.scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        LoginMenuApplication loginApp = new LoginMenuApplication(scanner, loggedInAccount, cardDAO);
        loginApp.start();

        String output = out.toString();

        assertTrue("Expected output contain error: 'Cannot add negative number'. Actual: " +
                output,
                output.contains("Cannot add negative number"));
    }

    /**
     * Simulates user selecting Log out and verifies that logged in is set to false and a log-out
     * message is printed to the screen.
     */
    @Test
    public void testLogOut() {
        String input = String.valueOf(LoginMenuResult.LOG_OUT.getValue());

        scanner = new Scanner(new ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        LoginMenuApplication loginApp = new LoginMenuApplication(scanner, loggedInAccount, cardDAO);
        loginApp.start();

        String output = out.toString();

        assertFalse("LoggedIn should be false",
                loginApp.isLoggedIn());

        assertTrue("You have successfully logged out! should appear on the screen",
                output.contains("You have successfully logged out!"));
    }
}