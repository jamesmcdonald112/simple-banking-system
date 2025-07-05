package banking.account;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountControllerTest {
    private Account testAccount;

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
     * Verifies that only one account exists, the one created in the before method.
     */
    @Test
    public void testCreateAccountOption() {
        assertEquals("AccountStore should contain exactly 1 account after setup",
                1,
                AccountStore.getAccounts().size());
    }

}