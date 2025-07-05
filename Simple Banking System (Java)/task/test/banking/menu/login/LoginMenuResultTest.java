package banking.menu.login;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginMenuResultTest {

    /**
     * Verifies that each input corresponds to the correct ENUM
     */
    @Test
    public void testFromValueReturnsCorrectEnum() {
        assertEquals("Input of 0 should return EXIT",
                LoginMenuResult.EXIT,
                LoginMenuResult.fromValue(0));

        assertEquals("Input of 1 should return BALANCE",
                LoginMenuResult.BALANCE,
                LoginMenuResult.fromValue(1));

        assertEquals("Input of 2 should return LOG_OUT",
                LoginMenuResult.LOG_OUT,
                LoginMenuResult.fromValue(2));
    }

    /**
     * Verifies that unknown input values return INVALID
     */
    @Test
    public void fromValueReturnsInvalidForUnknownInput() {
        assertEquals("Input of 999 should return INVALID",
                LoginMenuResult.INVALID,
                LoginMenuResult.fromValue(999));

        assertEquals("Input of -5 should return INVALID",
                LoginMenuResult.INVALID,
                LoginMenuResult.fromValue(-5));
    }
}