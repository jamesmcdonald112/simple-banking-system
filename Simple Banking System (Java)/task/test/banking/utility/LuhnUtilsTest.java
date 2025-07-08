package banking.utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class LuhnUtilsTest {

    /**
     * Validates that valid card numbers pass the Luhn check
     */
    @Test
    public void testValidCardNumberPassesLuhnCheck() {
        String[] validCards = {
                "4000008449433403",
                "4000001234567899",
                "4000000000000002"
        };

        for (String card : validCards) {
            assertTrue("Expected card to be valid: " + card,
                    LuhnUtils.isValid(card));
        }

    }


    /**
     * Validates that invalid card numbers fail the Luhn check
     */

    @Test
    public void testInvalidCardNumberFailsLuhnCheck() {
        String[] invalidCards = {
                "4000008449433404",
                "4000001234567890",
                "4000000000000003"
        };

        for (String card : invalidCards) {
            assertFalse("Expected card to be invalid: " + card,
                    LuhnUtils.isValid(card));
        }
    }

    @Test
    public void testCalculateChecksum() {
        String accountIdentifierAndBin = "400000844943340";
        int checksum = LuhnUtils.calculateChecksum(accountIdentifierAndBin);

        String validCardNumber = accountIdentifierAndBin + checksum;

        assertTrue("Expect card to be valid: " + validCardNumber,
                LuhnUtils.isValid(validCardNumber));
    }
}