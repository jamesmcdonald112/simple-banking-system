package banking.menu;

import banking.account.Account;
import banking.account.AccountStore;
import banking.login.LoginManager;

public class MenuController {

    /**
     * Creates a new account and adds it to the AccountStore
     */
    public static void handleCreateAccountOption() {
        Account account = new Account();
        AccountStore.addAccount(account);
    }

    /**
     * Handles a login attempt by verifying the provided card number and PIN.
     *
     * @param cardNumber The card number as a String
     * @param pin The PIN as a String
     * @return True if the login is successful; false otherwise
     */
    public static boolean handleLoginOption(String cardNumber, String pin) {
        return LoginManager.isValidLogin(cardNumber, pin);
    }
}
