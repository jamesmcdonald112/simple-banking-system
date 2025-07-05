package banking.menu.main;

public enum MainMenuResult {
    EXIT(0),
    CREATE_ACCOUNT(1),
    LOGIN(2),
    INVALID(-1);

    private final int value;

    MainMenuResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MainMenuResult fromValue(int input) {
        for (MainMenuResult result: values()) {
            if (result.value == input) return result;
        }
        return INVALID;
    }
}
