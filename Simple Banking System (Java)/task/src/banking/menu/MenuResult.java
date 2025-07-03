package banking.menu;

public enum MenuResult {
    EXIT(0),
    CREATE_ACCOUNT(1),
    LOGIN(2),
    INVALID(-1);

    private final int value;

    MenuResult(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuResult fromValue(int input) {
        for (MenuResult result: values()) {
            if (result.value == input) return result;
        }
        return INVALID;
    }
}
