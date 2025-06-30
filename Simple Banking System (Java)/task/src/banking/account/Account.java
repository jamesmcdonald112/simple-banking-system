package banking.account;

import banking.utility.NumberGenerator;

public class Account {
    private int balance;
    private String pin;
    private String cardNumber;

    /**
     * Constructs a new account with a balance of 0, a randomly generated 4-digit PIN, and
     * generates a card number.
     */
    public Account() {
        this.balance = 0;
        this.pin = NumberGenerator.generateRandomDigitString(4);
        this.cardNumber = "4000001234567890";
    }

//    Getters and Setters

    /**
     * Returns the current balance of the account
     *
     * @return current balance as an int
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Returns the pin of the account
     *
     * @return the pin of the account as a string
     */
    public String getPin() {
        return this.pin;
    }

    /**
     * Returns the card number of the account
     *
     * @return card number as a Stirng
     */
    public String getCardNumber(){
        return this.cardNumber;
    }

}
