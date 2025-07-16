package banking.menu.login;


public enum LoginMenuResult {
    EXIT(0, "Exit"),
    BALANCE(1, "Balance"),
    ADD_INCOME(2, "Add income"),
    DO_TRANSFER(3, "Do transfer"),
    CLOSE_ACCOUNT(3, "Close account"),
    LOG_OUT(5, "Log out"),
    INVALID(-1, "Invalid");

    private final int value;
    private final String label;

    /**
     * Constructs a LoginMenuResult with a corresponding integer value and String label.
     *
     * @param value The numeric representation of the menu option.
     * @param label The string  representation of the menu option.
     */
    LoginMenuResult(int value, String label) {
        this.value = value;
        this.label = label;
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
     * Returns the String value associated with this menu option.
     *
     * @return String value of menu option.
     */
    public String getLabel() {
        return label;
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
