package banking.menu.login;

import banking.account.Account;
import banking.database.CardDAO;

import java.util.Scanner;

public class LoginMenuApplication {
    private final Scanner scanner;
    private final Account loggedInAccount;
    private final CardDAO cardDAO;
    private boolean loggedIn;



    /**
     * Constructor that sets the scanner and the logged in account
     *
     * @param scanner The scanner to the set
     * @param loggedInAccount The logged in account to be set
     */
    public LoginMenuApplication(Scanner scanner, Account loggedInAccount, CardDAO cardDAO) {
        this.scanner = scanner;
        this.loggedInAccount = loggedInAccount;
        this.loggedIn = true;
        this.cardDAO = cardDAO;
    }

    /**
     * Returns the current logged-in status of the Account
     *
     * @return True if logged in; false otherwise
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Sets the logged in status of the account.
     *
     * @param loggedIn True if logged in; false otherwise
     */
    private void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /** Starts the login menu loop and handles user navigation. */
    public void start() {
        boolean running = true;

        while (running && loggedIn) {
            printMenuOptions();
            if (!scanner.hasNextLine()) break;
            String input = scanner.nextLine();
            LoginMenuResult choice = LoginMenuService.handleMenuInput(input);

            switch (choice) {
                case BALANCE -> handleShowBalance();
                case ADD_INCOME -> handleAddIncome();
                case DO_TRANSFER -> handleDoTransfer();
                case CLOSE_ACCOUNT -> handleCloseAccount();
                case LOG_OUT -> handleLogOut();
                case EXIT -> running = false;
                default -> System.out.println("Invalid option. Try Again.");
            }
        }
    }

    /** Prints the main menu options to the console. */
    private void printMenuOptions() {
        for (LoginMenuResult option : LoginMenuResult.values()) {
            if (option != LoginMenuResult.INVALID) {
                System.out.printf("%d. %s%n", option.getValue(), option.getLabel());
            }
        }
    }

    /**
     * Prints the logged in account balance to the screen
     */
    private void handleShowBalance() {
        int retrievedBalance = cardDAO.getBalanceByCardNumber(loggedInAccount.getCardNumber());
        System.out.println("Balance: " + retrievedBalance);
    }

    /**
     * Prompts the user to enter an income amount and adds it to their account balance.
     */
    private void handleAddIncome() {
        System.out.println("Enter income:");
        int income = scanner.nextInt();
        scanner.nextLine();
        cardDAO.addIncome(loggedInAccount.getCardNumber(), income);
    }

    private void handleDoTransfer() {

    }

    private void handleCloseAccount() {

    }

    /**
     * Prints a logged out message to the screen and sets loggedIn to false
     */
    private void handleLogOut() {
        System.out.println("You have successfully logged out!");
        setLoggedIn(false);
    }
}
