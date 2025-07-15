package banking.menu.main;

import banking.account.Account;
import banking.account.AccountController;
import banking.database.CardDAO;
import banking.login.LoginManager;
import banking.menu.login.LoginMenuApplication;

import java.util.Scanner;

public class MainMenuApplication {
    private final Scanner scanner;
    private final CardDAO cardDAO;
    private Account loggedInAccount = null;

    public MainMenuApplication(Scanner scanner, CardDAO cardDAO) {
        this.scanner = scanner;
        this.cardDAO = cardDAO;
    }


    /** Starts the main menu loop and handles user navigation. */
    public void start() {
        boolean running = true;

        while (running) {
            printMenuOptions();
            String input = scanner.nextLine();
            System.err.println("DEBUG: Read input -> " + input);
            MainMenuResult choice = MainMenuService.handleMenuInput(input);

            switch (choice) {
                case CREATE_ACCOUNT -> handleCreateAccount();
                case LOGIN -> handleLogin();
                case EXIT -> {
                    System.exit(0);
                }
                default -> System.out.println("Invalid option. Try Again.");
            }
        }

    }

    /**
     * The account currently logged in.
     *
     * @return The logged in account as Account
     */
    public Account getLoggedInAccount() {
        return loggedInAccount;
    }

    private void setLoggedInAccount(Account loggedInAccount) {
        this.loggedInAccount = loggedInAccount;
    }

    /** Prints the main menu options to the console. */
    private void printMenuOptions() {
        System.out.println("""
                1. Create an account
                2. Log into account
                0. Exit""");
    }

    /**
     * Creates a new account, stores it in AccountStore, sets the logged in account,
     * and prints the account details to the console.
     */
    private void handleCreateAccount() {
        Account createdAccount = AccountController.handleCreateAccountOption(cardDAO);
        printAccountInformation(createdAccount.getCardNumber(), createdAccount.getPin());

    }

    /**
     * Prints the generated card number and PIN to the console.
     *
     * @param cardNumber The generated card number
     * @param pin        The generated PIN
     */
    private void printAccountInformation(String cardNumber, String pin) {
        System.out.println("Your card has been created");
        System.out.println("Your card number:\n" + cardNumber);
        System.out.println("Your card PIN:\n" + pin);
        System.out.println("");
    }

    /**
     * Prompts the user for their card number and pin, verifies that it matches an account in
     * AccountStore, and prints a success message if correct, or a failure message if incorrect
     */
    private void handleLogin() {
        System.out.println("Enter your card number:\n");
        String cardNumber = scanner.nextLine();
        System.out.println("Enter your PIN:\n");
        String pin = scanner.nextLine();

        if (LoginManager.isValidLogin(cardNumber, pin, cardDAO)) {
            System.out.println("You have successfully logged in!");
            setLoggedInAccount(cardDAO.findByCardAndPin(cardNumber, pin));
            LoginMenuApplication loginMenu = new LoginMenuApplication(scanner, loggedInAccount);
            loginMenu.start();
        } else {
            System.out.println("Wrong card number or PIN!");
        }
    }
}
