package banking.login;

import banking.database.CardDAO;

public class LoginManager {

    /**
     * Checks whether the provided card number and PIN match a valid account in the database.
     *
     * @param cardNumber the card number to verify
     * @param pin        the PIN associated with the card
     * @param dao        the CardDAO instance used to access the database
     * @return {@code true} if the login credentials are valid; {@code false} otherwise
     */
    public static boolean isValidLogin(String cardNumber, String pin, CardDAO dao) {
        return dao.findByCardAndPin(cardNumber, pin) != null;
    }
}
