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

    /**
     * Tests that a newly created Account should have a PIN of length 4
     */
    @Test
    public void testPinIsFourDigits() {
        String pin = account.getPin();

        assertTrue("PIN should only contain only digits. PIN: " + pin,
                pin.matches("\\d+"));

        assertEquals("PIN should be four digits in length",
                4,
                pin.length());
    }

    /**
     * Verifies that the card number is 16 digits and only numeric values.
     */
    @Test
    public void testCardNumberIs16DigitsAndNumeric() {
        String cardNumber = account.getCardNumber();

        assertTrue("Card number should be 16 digits in length. Actual length: "  + cardNumber.length(),
                cardNumber.length() == 16);

        assertTrue("Card number should only contain numeric characters. Card number: " + cardNumber,
                cardNumber.matches("\\d+"));
    }
}