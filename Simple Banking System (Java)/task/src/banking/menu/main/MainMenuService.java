package banking.menu.main;

public class MainMenuService {

    /**
     * Converts the user-provided menu input string into the corresponding MenuResult enum.
     * Returns INVALID if input is not a valid integer or does not match any menu option.
     *
     * @param input Input as a String
     * @return The MenuResult
     */
    public static MainMenuResult handleMenuInput(String input) {
        int parsed = banking.utility.InputParser.safeParseInt(input, MainMenuResult.INVALID.getValue());
        return MainMenuResult.fromValue(parsed);
    }
}
