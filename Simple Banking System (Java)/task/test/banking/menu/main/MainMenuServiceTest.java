package banking.menu.main;

import org.junit.Test;

import static org.junit.Assert.*;

public class MainMenuServiceTest {

    /**
     * Verifies that an input of "0" should return EXIT
     */
    @Test
    public void testUserInput0ReturnsExit() {
        MainMenuResult menuResult = MainMenuService.handleMenuInput("0");
        assertEquals("An input of 0 should return EXIT",
                MainMenuResult.EXIT,
                menuResult);
    }

    /**
     * Verifies that an input of "1" should return CREATE_ACCOUNT
     */
    @Test
    public void testUserInput1ReturnsCreateAccount() {
        MainMenuResult menuResult = MainMenuService.handleMenuInput("1");
        assertEquals("An input of 1 should return CREATE_ACCOUNT",
                MainMenuResult.CREATE_ACCOUNT,
                menuResult);
    }

    /**
     * Verifies that an input of "2" should return LOGIN
     */
    @Test
    public void testUserInput2ReturnsLogin() {
        MainMenuResult menuResult = MainMenuService.handleMenuInput("2");
        assertEquals("An input of 2 should return LOGIN",
                MainMenuResult.LOGIN,
                menuResult);
    }

    /**
     * Verifies that INVALID is returned for invalid inputs
     */
    @Test
    public void testInvalidInputReturnsInvalid() {
        assertEquals("Input of '999' should be invalid",
                MainMenuResult.INVALID,
                MainMenuService.handleMenuInput("999"));

        assertEquals("Input of abc should be invalid",
                MainMenuResult.INVALID,
                MainMenuService.handleMenuInput("abc"));

        assertEquals("Input of '' should be invalid",
                MainMenuResult.INVALID,
                MainMenuService.handleMenuInput(""));
    }

}