package banking.menu;

import banking.account.Account;

import java.util.Scanner;

public class MenuApplication {
    private final Scanner scanner;

    public MenuApplication(Scanner scanner) {
        this.scanner = scanner;
    }


    /** Starts the main menu loop and handles user navigation. */
    public void start() {
        boolean running = true;

        while (running) {
            printMenuOptions();
            String input = scanner.nextLine();
            MenuResult choice = MenuService.handleMenuInput(input);

            switch (choice) {
                case CREATE_ACCOUNT -> handleCreateAccount();
                case LOGIN -> handleLogin();
                case EXIT -> running = false;
                default -> System.out.println("Invalid option. Try Again.");
            }
        }

    }

    /** Prints the main menu options to the console. */
    private void printMenuOptions() {
        System.out.println("""
                1. Create an account
                2. Log into account
                0. Exit""");
    }

    /**
     * Creates a new account, stores it in AccountStore,
     * and prints the account details to the console.
     */
    private void handleCreateAccount() {
        Account createdAccount = MenuController.handleCreateAccountOption();
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

    private void handleLogin() {

    }
}
