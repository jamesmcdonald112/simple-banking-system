package banking.account;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class AccountTest {
    private Account account;

    /**
     * Initialises a new Account before each test method
     */
    @Before
    public void setup() {
        account = new Account();
    }

    /**
     * Tests that a newly created Account should have a balance of 0
     */
    @Test
    public void testDefaultBalanceIsZero() {
        assertEquals("New accounts balance should equal: " + 0,
                0,
                account.getBalance());
    }

}