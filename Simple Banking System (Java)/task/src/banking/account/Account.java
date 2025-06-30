package banking.account;

import banking.utility.NumberGenerator;

public class Account {
    private int balance;
    private String pin;

    /**
     * Constructs a new account with a balance of 0 and a randomly generated 4-digit PIN.
     */
    public Account() {
        this.balance = 0;
        this.pin = NumberGenerator.generateRandomDigitString(4);
    }

//    Getters and Setters

    /**
     * Returns the current balance of the account
     *
     * @return current balance as an int
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Returns the pin of the account
     *
     * @return the pin of the account as a string
     */
    public String getPin() {
        return pin;
    }
}
