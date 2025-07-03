package banking.menu;

import org.junit.Test;

import static org.junit.Assert.*;

public class MenuResultTest {

    /**
     * Verifies that each input corresponds to the correct ENUM
     */
    @Test
    public void testFromValueReturnsCorrectEnum() {
        assertEquals("Input of 0 should return EXIT",
                MenuResult.EXIT,
                MenuResult.fromValue(0));

        assertEquals("Input of 1 should return CREATE_ACCOUNT",
                MenuResult.CREATE_ACCOUNT,
                MenuResult.fromValue(1));

        assertEquals("Input of 2 should return LOGIN",
                MenuResult.LOGIN,
                MenuResult.fromValue(2));
    }

    /**
     * Verifies that unknown input values return INVALID
     */
    @Test
    public void fromValueReturnsInvalidForUnknownInput() {
        assertEquals("Input of 999 should return INVALID",
                MenuResult.INVALID,
                MenuResult.fromValue(999));

        assertEquals("Input of -5 should return INVALID",
                MenuResult.INVALID,
                MenuResult.fromValue(-5));
    }
}
