package banking.menu;

import banking.account.Account;
import banking.account.AccountStore;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuControllerTest {

    /**
     * Clears the AccountStore of any accounts before each test
     */
    @Before
    public void setUp() {
        AccountStore.clearAccounts();
    }

    /**
     * Verifies that no accounts exist at the start and that one is added after selecting
     * "create account".
     */
    @Test
    public void testCreateAccountOption() {
        assertTrue("No accounts should be in Account store before creating an account",
                AccountStore.getAccounts().size() == 0);

        MenuController menu = new MenuController();
        menu.handleCreateAccountOption();

        assertEquals("Exactly one account should be added after account creation",
                1,
                AccountStore.getAccounts().size());
    }

    /**
     * Creates a new account and adds it to the AccountStore.
     * Instantiates a new MenuController and verifies that a valid login attempt succeeds.
     */
    @Test
    public void testHandleLoginOption() {
        Account account = new Account();
        String cardNumber = account.getCardNumber();
        String pin = account.getPin();
        AccountStore.addAccount(account);

        MenuController menu = new MenuController();

        boolean isValidLogin = menu.handleLoginOption(cardNumber, pin);

        assertTrue("Login should succeed with a valid card number and PIN",
                isValidLogin);
    }

}