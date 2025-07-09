package banking.database;

public class DatabaseConfig {

    /**
     * Parses the database file name from the command-line arguments.
     *
     * @param args The array of command-line arguments.
     * @return The database file name if valid, otherwise null.
     */
    public static String parseDbFileName(String[] args) {
        return (args.length == 2 && "-fileName".equals(args[0])) ? args[1] : null;
    }
}
