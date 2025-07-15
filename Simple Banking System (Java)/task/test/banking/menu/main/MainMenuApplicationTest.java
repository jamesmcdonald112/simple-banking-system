package banking.menu.main;

import banking.account.Account;
import banking.database.CardDAO;
import banking.utility.database.DatabaseTestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class MainMenuApplicationTest {
    private Scanner scanner;
    private CardDAO dao;
    private String databaseName;
    private Connection conn;


    /**
     * Clears the account store before each test to ensure a clean state.
     */
    @Before
    public void setup() {
        this.databaseName = "testMainMenuApplication.s3db";
        try {
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            this.dao = new CardDAO(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.dao.ensureCardTableExists();
    }

    /**
     * Closes the scanner after each test to free system resources.
     */
    @After
    public void close() {
        DatabaseTestUtils.deleteDatabaseFile(databaseName);
        if (scanner != null) {
            scanner.close();
        }
    }

    /**
     * Simulates user selecting "1" (create account) followed by "0" (exit),
     * and verifies that one account is created and stored.
     */
    @Test
    public void testCreateAccountThenExit() {
        scanner = createScannerWithInput("1\n0\n");

        MainMenuApplication app = new MainMenuApplication(scanner, dao);
        app.start();

        assertEquals("After creating an Account, the total size in AccountStore should be 1",
                1,
                dao.countAllCards());
    }

    /**
     * Creates an account and stores it in AccountStore, simulates user selecting "2"
     * (login), entering card number and pin, and then "0" (exit).
     */
    @Test
    public void testSuccessfulLoginThenExit() {
        // Create account to know credentials
        Account account = new Account();
        this.dao.addCard(account.getCardNumber(), account.getPin(), account.getBalance());

        String userInput = String.join("\n",
                "2", // Login
                account.getCardNumber(),
                account.getPin(),
                "0", // Exit logged in menu
                "0"// Exit main menu
        ) + "\n";
        scanner = createScannerWithInput(userInput);


        MainMenuApplication app = new MainMenuApplication(scanner, dao);
        app.start();

        assertEquals("Logged in account should match the credentials used",
                account.getCardNumber(),
                app.getLoggedInAccount().getCardNumber());
    }

    /**
     * Creates an account and stores it in AccountStore, simulates user selecting "2"
     * (login), entering incorrect card number and pin, and then "0" (exit).
     */
    @Test
    public void testInvalidLogin() {
        Account account = new Account();
        this.dao.addCard(account.getCardNumber(), account.getPin(), account.getBalance());

        String userInput = String.join("\n",
                "2", "wrongCard", "wrongPin", "0") + "\n";
        scanner = createScannerWithInput(userInput);

        MainMenuApplication app = new MainMenuApplication(scanner, dao);
        app.start();

        assertNull("No account should be logged in with invalid credentials", app.getLoggedInAccount());
    }

    @Test
    public void testShowLoggedInMenu() {
        PrintStream originalOut = System.out;
        // Create account to know credentials
        Account account = new Account();
        this.dao.addCard(account.getCardNumber(), account.getPin(), account.getBalance());

        String userInput = String.join("\n",
                "2", // Login
                account.getCardNumber(),
                account.getPin(),
                "1", // Balance
                "2", // Log-out
                "0", // Exit Logged in menu
                "0" // Exit main menu
        ) + "\n";

        scanner = new Scanner(new ByteArrayInputStream(userInput.getBytes()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        MainMenuApplication app = new MainMenuApplication(scanner, dao);
        app.start();

        String output = out.toString();

        assertTrue("Expected menu option '1. Balance' was not found in output:\n" + output,
                output.contains("1. Balance"));

        assertTrue("Expected account balance 'Balance: 0' was not found in output:\n" + output,
                output.contains("Balance: 0"));

        assertTrue("Expected logout message 'You have successfully logged out!' was not found in output:\n" + output,
                output.contains("You have successfully logged out!"));
        System.setOut(originalOut);
    }

    /**
     * Creates a Scanner from a given user input string, simulating console input.
     *
     * @param userInput The string input to simulate user choices.
     * @return A Scanner instance reading from the simulated input.
     */
    private Scanner createScannerWithInput(String userInput) {
        return new Scanner(generateInputStream(userInput));
    }

    /**
     * Converts a string into an InputStream of bytes, for test input simulation.
     *
     * @param userInput The user input string.
     * @return An InputStream representing the byte version of the input.
     */
    private InputStream generateInputStream(String userInput) {
        return new ByteArrayInputStream(userInput.getBytes());
    }
}