package banking.account;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AccountStoreTest {

    /**
     * Before each test the accountStore is cleared to ensure the list is empty for testing.
     */
    @Before
    public void setUp() {
        // Ensure that all the accounts are removed.
        AccountStore.clearAccounts();
    }

    /**
     * Verifies that adding a new Account results in it being stored in the AccountStore,
     * and that the stored account matches the one added.
     */
    @Test
    public void testAddAccount() {
        Account account = new Account();
        AccountStore.addAccount(account);

        List<Account> accountsList = AccountStore.getAccounts();
        assertEquals("The accounts size should be 1",
                1,
                accountsList.size());

        assertEquals("The added account should match",
                account,
                accountsList.get(0));
    }

    /**
     * Verifies that the list of accounts is cleared after running the clearAccounts method.
     */
    @Test
    public void testClearAccount() {
        Account newAccount = new Account();
        AccountStore.addAccount(newAccount);

        assertEquals("The account list should be 1",
                1,
                AccountStore.getAccounts().size());

        AccountStore.clearAccounts();
        assertEquals("The account list should be 0",
                0,
                AccountStore.getAccounts().size());
    }

    /**
     * Verifies that an account can be found in AccountStore using its card number and PIN.
     */
    @Test
    public void testFindByCardAndPin() {
        Account newAccount = new Account();
        String accountCard = newAccount.getCardNumber();
        String accountPin = newAccount.getPin();

        AccountStore.addAccount(newAccount);

        Account retrievedAccount = AccountStore.findByCardAndPin(accountCard, accountPin);
        assertEquals("The retrieved account should match the originally added account",
                newAccount,
                retrievedAccount);
    }
}