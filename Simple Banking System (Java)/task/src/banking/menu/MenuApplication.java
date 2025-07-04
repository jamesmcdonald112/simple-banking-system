package banking.menu;

import banking.account.Account;
import banking.account.AccountStore;

import java.util.Scanner;

public class MenuApplication {
    private final Scanner scanner;

    public MenuApplication(Scanner scanner) {
        this.scanner = scanner;
    }


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

    private void printMenuOptions() {
        System.out.println("""
                1. Create an account
                2. Log into account
                0. Exit""");
    }

    private void handleCreateAccount() {
        Account createdAccount = MenuController.handleCreateAccountOption();
        String cardNumber = createdAccount.getCardNumber();
        String pin = createdAccount.getPin();

        System.out.println("Your card has been created");
        System.out.println("Your card number:\n" + cardNumber);
        System.out.println("Your card PIN:\n" + pin);
        System.out.println("");
    }

    private void handleLogin() {

    }
}
