package banking.runner;

import banking.database.CardDAO;
import banking.database.DatabaseConfig;
import banking.database.DatabaseManager;
import banking.menu.main.MainMenuApplication;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        String dbFileName = DatabaseConfig.parseDbFileName(args);

        DatabaseManager dbManager = new DatabaseManager();
        if (!dbManager.connectTo(dbFileName)) {
            System.out.println("Could not connect to database.");
        }
        Scanner scanner = new Scanner(System.in);

        CardDAO dao = new CardDAO(dbManager.getConnection());
        dao.ensureCardTableExists();

        MainMenuApplication menu = new MainMenuApplication(scanner, dao);
        try {
            menu.start();
        } finally {
            dbManager.closeConnection();
            scanner.close();
        }
    }
}
