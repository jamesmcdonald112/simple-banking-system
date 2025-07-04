package banking.menu;

import banking.account.AccountStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MenuApplicationTest {
    private Scanner scanner;

    /**
     * Clears the account store before each test to ensure a clean state.
     */
    @Before
    public void setup() {
        AccountStore.clearAccounts();
    }

    /**
     * Simulates user selecting "1" (create account) followed by "0" (exit),
     * and verifies that one account is created and stored.
     */
    @Test
    public void testCreateAccountThenExit() {
        scanner = createScannerWithInput("1\n0\n");

        MenuApplication app = new MenuApplication(scanner);
        app.start();

        assertEquals("After creating an Account, the total size in AccountStore should be 1",
                1,
                AccountStore.getAccounts().size());
    }

    /**
     * Closes the scanner after each test to free system resources.
     */
    @After
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
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