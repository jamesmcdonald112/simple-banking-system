package banking.runner;

import banking.database.CardDAO;
import banking.database.DatabaseConfig;
import banking.menu.main.MainMenuApplication;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        String dbName = DatabaseConfig.parseDbFileName(args);
        CardDAO dao = new CardDAO(dbName);
        dao.ensureCardTableExists();

        Scanner scanner = new Scanner(System.in);
        MainMenuApplication menu = new MainMenuApplication(scanner);
        menu.start();
        scanner.close();
    }
}
