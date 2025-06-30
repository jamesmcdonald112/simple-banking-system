package banking.account;

public class Account {
    private int balance;

    public Account() {
        this.balance = 0;
    }

    /**
     *Returns the current balance of the account
     *
     * @return the current account balance as an int
     */
    public int getBalance() {
        return balance;
    }
}
