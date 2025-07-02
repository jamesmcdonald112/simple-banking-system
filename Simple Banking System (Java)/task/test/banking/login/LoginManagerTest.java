package banking.login;

import banking.account.Account;
import banking.account.AccountStore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginManagerTest {
    private Account testAccount;

    /**
     * Sets up a fresh test environment before each test by:
     * - Clearing all accounts from the AccountStore
     * - Creating and adding a new test account
     */
    @Before
    public void setUp() {
        AccountStore.clearAccounts(); // Clean slate
        testAccount = new Account();
        AccountStore.addAccount(testAccount);
    }

    /**
     * Verifies that a valid card number and PIN combination is recognised as a valid login
     */
    @Test
    public void testIsValidLogin() {
        boolean isValidLogin = LoginManager.isValidLogin(testAccount.getCardNumber(),
                testAccount.getPin());

        assertTrue("Login should succeed for a valid card and PIN",
                isValidLogin);
    }
}