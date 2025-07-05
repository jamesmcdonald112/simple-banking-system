package banking.menu.login;


public class LoginMenuService {
    /**
     * Converts the user-provided login menu input string into the corresponding LoginMenuResult
     * enum.
     * Returns INVALID if input is not a valid integer or does not match any menu option.
     *
     * @param input Input as a String
     * @return The LoginMenuResult
     */
    public static LoginMenuResult handleMenuInput(String input) {
        int parsed = banking.utility.InputParser.safeParseInt(input, LoginMenuResult.INVALID.getValue());
        return LoginMenuResult.fromValue(parsed);
    }
}
