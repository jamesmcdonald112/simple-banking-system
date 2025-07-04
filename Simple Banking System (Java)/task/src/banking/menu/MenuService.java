package banking.menu;

public class MenuService {

    /**
     * Converts the user-provided menu input string into the corresponding MenuResult enum.
     * Returns INVALID if input is not a valid integer or does not match any menu option.
     *
     * @param input Input as a String
     * @return The MenuResult
     */
    public static MenuResult handleMenuInput(String input) {
        int parsed = parseIntFromString(input);
        return MenuResult.fromValue(parsed);
    }

    /**
     * Attempts to parse an integer from the input string
     *
     * @param input Input as a String
     * @return Parsed integer, or -1 if parsing fails
     */
    private static int parseIntFromString(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return MenuResult.INVALID.getValue();
        }
    }
}
