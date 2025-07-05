package banking.login;

import banking.account.Account;
import banking.account.AccountStore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginServiceTest {
    private Account testAccount;
    private LoginService loginService;

    /**
     * Clears the AccountStore of any accounts before each test
     */
    @Before
    public void setUp() {
        AccountStore.clearAccounts();
        testAccount = new Account();
        AccountStore.addAccount(testAccount);
    }

    /**
     * Verifies that a valid login attempt succeeds.
     */
    @Test
    public void testHandleLoginOptionSuccess() {
        boolean isValidLogin = loginService.handleLoginOption(testAccount.getCardNumber(),
                testAccount.getPin());

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
        boolean resultWrongCard = loginService.handleLoginOption(dummyCardNumber, testAccount.getPin());
        assertFalse("Login should not succeed with an invalid card number",
                resultWrongCard);

        // Incorrect PIN
        boolean resultWrongPin = loginService.handleLoginOption(testAccount.getCardNumber(), dummyPin);
        assertFalse("Login should not succeed with an invalid PIN",
                resultWrongPin);
    }

}