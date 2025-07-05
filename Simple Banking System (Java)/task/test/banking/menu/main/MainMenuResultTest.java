package banking.menu.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainMenuResultTest {

    /**
     * Verifies that each input corresponds to the correct ENUM
     */
    @Test
    public void testFromValueReturnsCorrectEnum() {
        assertEquals("Input of 0 should return EXIT",
                MainMenuResult.EXIT,
                MainMenuResult.fromValue(0));

        assertEquals("Input of 1 should return CREATE_ACCOUNT",
                MainMenuResult.CREATE_ACCOUNT,
                MainMenuResult.fromValue(1));

        assertEquals("Input of 2 should return LOGIN",
                MainMenuResult.LOGIN,
                MainMenuResult.fromValue(2));
    }

    /**
     * Verifies that unknown input values return INVALID
     */
    @Test
    public void fromValueReturnsInvalidForUnknownInput() {
        assertEquals("Input of 999 should return INVALID",
                MainMenuResult.INVALID,
                MainMenuResult.fromValue(999));

        assertEquals("Input of -5 should return INVALID",
                MainMenuResult.INVALID,
                MainMenuResult.fromValue(-5));
    }
}
