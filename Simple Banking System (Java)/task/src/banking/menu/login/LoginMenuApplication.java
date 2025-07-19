package banking.menu.login;

import banking.account.Account;
import banking.database.CardDAO;
import banking.database.DatabaseManager;
import banking.utility.LuhnUtils;

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
        if (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            try {
                int income = Integer.parseInt(line);
                if (income < 0) {
                    System.out.println("Cannot add negative number");
                } else {
                    cardDAO.addIncome(loggedInAccount.getCardNumber(), income);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number");
            }
        }

    }

    /**
     * Prompts the user to enter the card number they wish to transfer to.
     */
    private void handleDoTransfer() {
        System.out.println("Transfer");
        System.out.println("Enter card number:");
        if (!this.scanner.hasNextLine()) return;

        String cardNumberToTransfer = scanner.nextLine().trim();

        if (cardNumberToTransfer.equals(loggedInAccount.getCardNumber())) {
                System.out.println("You can't transfer money to the same account!");
                return;
            }

        if (!LuhnUtils.isValid(cardNumberToTransfer)) {
            System.out.println("Probably you made a mistake in the card number. Please try again!");
            return;
        }
        if (this.cardDAO.findByCard(cardNumberToTransfer) == null) {
            System.out.println("Such a card does not exist.");
            return;
        }

        System.out.println("Enter how much money you want to transfer:");
        if (!this.scanner.hasNextLine()) return;

        try {
            int amount = Integer.parseInt(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Amount must be greater than 0");
                return;
            }
            int currentBalance =
                    this.cardDAO.getBalanceByCardNumber(this.loggedInAccount.getCardNumber());
            if (amount > currentBalance) {
                System.out.println("Not enough money!");
                return;
            }

            boolean isSuccessfulTransfer =
                    this.cardDAO.transferFunds(this.loggedInAccount.getCardNumber(),
                    cardNumberToTransfer
                    , amount);

            if (isSuccessfulTransfer) {
                System.out.println("Success!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number from transfer amount: " + e.getMessage());
        }
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
