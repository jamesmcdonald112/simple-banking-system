package banking.account;

import java.util.ArrayList;
import java.util.List;

public class AccountStore {
    private static List<Account> accountsList = new ArrayList<Account>();

    /**
     * Returns a list of the accounts as List
     * @return the list of accounts as an arrayList
     */
    public static List<Account> getAccounts() {
        return new ArrayList<>(accountsList);
    }

    /**
     * Removes all accounts from the list of accounts
     */
    public static void clearAccounts() {
        accountsList.clear();
    }

    /**
     * Adds an account to the list of accounts
     *
     * @param account the account to be added to the list
     */
    public static void addAccount(Account account) {
        accountsList.add(account);
    }
}
