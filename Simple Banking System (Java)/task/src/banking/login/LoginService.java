package banking.login;

public class LoginService {

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
