package banking.menu.login;

import banking.account.Account;
import banking.menu.main.MainMenuResult;
import banking.menu.main.MainMenuService;

import java.util.Scanner;

public class LoginMenuApplication {
    private final Scanner scanner;
    private final Account loggedInAccount;
    private boolean loggedIn;


    /**
     * Constructor that sets the scanner and the logged in account
     *
     * @param scanner The scanner to the set
     * @param loggedInAccount The logged in account to be set
     */
    public LoginMenuApplication(Scanner scanner, Account loggedInAccount) {
        this.scanner = scanner;
        this.loggedInAccount = loggedInAccount;
        this.loggedIn = true;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    private void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /** Starts the login menu loop and handles user navigation. */
    public void start() {
        boolean running = true;

        while (running && loggedIn) {
            printMenuOptions();
            String input = scanner.nextLine();
            LoginMenuResult choice = LoginMenuService.handleMenuInput(input);

            switch (choice) {
                case BALANCE -> handleShowBalance();
                case LOG_OUT -> handleLogOut();
                case EXIT -> running = false;
                default -> System.out.println("Invalid option. Try Again.");
            }
        }
    }

    /** Prints the main menu options to the console. */
    private void printMenuOptions() {
        System.out.println("""
                1. Balance
                2. Log out
                0. Exit""");
    }

    /**
     * Prints the logged in account balance to the screen
     */
    private void handleShowBalance() {
        System.out.println("Balance: " + loggedInAccount.getBalance());
    }

    /**
     * Prints a logged out message to the screen and sets loggedIn to false
     */
    private void handleLogOut() {
        System.out.println("You have successfully logged out!");
        setLoggedIn(false);
    }
}
