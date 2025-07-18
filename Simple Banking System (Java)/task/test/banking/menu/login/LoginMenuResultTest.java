package banking.menu.login;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginMenuResultTest {


    /**
     * Verifies that each input corresponds to the correct ENUM
     */
    @Test
    public void testFromValueReturnsCorrectEnum() {
        for (LoginMenuResult result : LoginMenuResult.values()) {
            if (result != LoginMenuResult.INVALID) {
                assertEquals("Input of " + result.getValue() + " should return " + result,
                        result,
                        LoginMenuResult.fromValue(result.getValue()));
            }
        }
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