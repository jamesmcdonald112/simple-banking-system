package banking.account;

import banking.card.CardNumberGenerator;
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
        this.cardNumber = CardNumberGenerator.generateCardNumber();
    }

    /**
     * Constructs an account with given card info such as card number, PIN, and balance.
     *
     * @param cardNumber The card number as a String
     * @param pin The PIN as a String
     * @param balance The balance as an int
     */
    public Account(String cardNumber, String pin, int balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
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
     * @return card number as a String
     */
    public String getCardNumber(){
        return this.cardNumber;
    }

}
