package banking.menu.login;


public enum LoginMenuResult {
    EXIT(0),
    BALANCE(1),
    LOG_OUT(2),
    INVALID(-1);

    private final int value;

    /**
     * Constructs a LoginMenuResult with a corresponding integer value.
     *
     * @param value The numeric representation of the menu option.
     */
    LoginMenuResult(int value) {
        this.value = value;
    }

    /**
     * Returns the numeric value associated with this menu option.
     *
     * @return Integer value of the menu option.
     */
    public int getValue() {
        return value;
    }

    /**
     * Maps a numeric input to a corresponding LoginMenuResult.
     * If the input does not match any known value, returns INVALID.
     *
     * @param input The integer input from the user.
     * @return The matching LoginMenuResult, or INVALID if no match.
     */
    public static LoginMenuResult fromValue(int input) {
        for (LoginMenuResult result: values()) {
            if (result.value == input) return result;
        }
        return INVALID;
    }
}
