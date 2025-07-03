package banking.menu;

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

        assertEquals("AccountStore size should be 1",
                1,
                AccountStore.getAccounts().size());
    }


}