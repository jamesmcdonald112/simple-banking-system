package banking.menu;

import banking.account.Account;
import banking.account.AccountStore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuControllerTest {
    private Account testAccount;
    private MenuController menuController;

    /**
     * Clears the AccountStore of any accounts before each test
     */
    @Before
    public void setUp() {
        AccountStore.clearAccounts();
        testAccount = new Account();
        AccountStore.addAccount(testAccount);
        menuController = new MenuController();
    }

    /**
     * Verifies that only one account exists, the one created in the before method.
     */
    @Test
    public void testCreateAccountOption() {
        assertEquals("AccountStore should contain exactly 1 account after setup",
                1,
                AccountStore.getAccounts().size());
    }

    /**
     * Verifies that a valid login attempt succeeds.
     */
    @Test
    public void testHandleLoginOptionSuccess() {
        boolean isValidLogin = menuController.handleLoginOption(testAccount.getCardNumber(),
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
        boolean resultWrongCard = menuController.handleLoginOption(dummyCardNumber, testAccount.getPin());
        assertFalse("Login should not succeed with an invalid card number",
                resultWrongCard);

        // Incorrect PIN
        boolean resultWrongPin = menuController.handleLoginOption(testAccount.getCardNumber(), dummyPin);
        assertFalse("Login should not succeed with an invalid PIN",
                resultWrongPin);
    }

}