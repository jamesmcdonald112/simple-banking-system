package banking.login;

import banking.account.AccountStore;

public class LoginManager {

    public static boolean isValidLogin(String cardNumber, String pin) {
        return AccountStore.findByCardAndPin(cardNumber, pin) != null;
    }
}
