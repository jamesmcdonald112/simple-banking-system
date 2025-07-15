package banking.account;

import banking.database.CardDAO;

public class AccountController {

    /**
     * Creates a new account, adds it to the card database, and returns it.
     *
     * @param dao The CardDAO used to persist the new account.
     * @return The newly created Account.
     */
    public static Account handleCreateAccountOption(CardDAO dao) {
        Account account = new Account();
        dao.addCard(account.getCardNumber(), account.getPin(), account.getBalance());
        return account;
    }
}
