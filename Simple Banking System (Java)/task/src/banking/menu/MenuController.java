package banking.menu;

import banking.account.Account;
import banking.account.AccountStore;

public class MenuController {

    /**
     * Creates a new account and adds it to the AccountStore
     */
    public static void handleCreateAccountOption() {
        Account account = new Account();
        AccountStore.addAccount(account);
    }

}
