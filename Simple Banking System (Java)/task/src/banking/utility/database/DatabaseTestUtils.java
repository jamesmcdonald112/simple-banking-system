package banking.utility.database;

import java.io.File;

public class DatabaseTestUtils {

    /**
     * Deletes the database directory
     *
     * @param fileName The database to be deleted.
     */
    public static void deleteDatabaseFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }
}
