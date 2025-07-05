package banking.menu.login;

import banking.account.Account;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class LoginMenuApplicationTest {
    private Scanner scanner;
    private Account loggedInAccount;

    /**
     * Creates a new account to test login
     */
    @Before
    public void setup() {
        loggedInAccount = new Account();
    }

    /**
     * Closes the scanner after each test.
     */
    @After
    public void teardown() {
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

        LoginMenuApplication loginApp = new LoginMenuApplication(scanner, loggedInAccount);
        loginApp.start();

        String output = out.toString();

        assertTrue("Expected balance output to contain 'Balance: 0'",
                output.contains("Balance: 0"));

    }
}