package banking.account;

public class AccountController {

    /**
     *
     * Creates a new account and adds it to the AccountStore
     *
     * @return The newly created account
     */
    public static Account handleCreateAccountOption() {
        Account account = new Account();
        AccountStore.addAccount(account);
        return account;
    }
}
