package banking.menu;

import org.junit.Test;

import static org.junit.Assert.*;

public class MenuServiceTest {

    /**
     * Verifies that an input of "0" should return EXIT
     */
    @Test
    public void testUserInput0ReturnsExit() {
        MenuResult menuResult = MenuService.handleMenuInput("0");
        assertEquals("An input of 0 should return EXIT",
                MenuResult.EXIT,
                menuResult);
    }

    /**
     * Verifies that an input of "1" should return CREATE_ACCOUNT
     */
    @Test
    public void testUserInput1ReturnsCreateAccount() {
        MenuResult menuResult = MenuService.handleMenuInput("1");
        assertEquals("An input of 1 should return CREATE_ACCOUNT",
                MenuResult.CREATE_ACCOUNT,
                menuResult);
    }

    /**
     * Verifies that an input of "2" should return LOGIN
     */
    @Test
    public void testUserInput2ReturnsLogin() {
        MenuResult menuResult = MenuService.handleMenuInput("2");
        assertEquals("An input of 2 should return LOGIN",
                MenuResult.LOGIN,
                menuResult);
    }

    /**
     * Verifies that INVALID is returned for invalid inputs
     */
    @Test
    public void testInvalidInputReturnsInvalid() {
        assertEquals("Input of '999' should be invalid",
                MenuResult.INVALID,
                MenuService.handleMenuInput("999"));

        assertEquals("Input of abc should be invalid",
                MenuResult.INVALID,
                MenuService.handleMenuInput("abc"));

        assertEquals("Input of '' should be invalid",
                MenuResult.INVALID,
                MenuService.handleMenuInput(""));
    }

}