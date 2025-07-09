package banking.database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class CardDAOTest {

    /**
     * Ensures that the 'card' table exists in the database.
     * If it does not exist, it will be created.
     */
    @Test
    public void createTable() {
        String dbName = "test.s3db";
        CardDAO dao = new CardDAO(dbName);
        dao.ensureCardTableExists();

        boolean exists = tableExists("card", dbName);
        assertTrue("Card table should exist",
                exists);
    }


    /**
     * Checks whether a given table exists in the specified SQLite database
     * using metadata from the database connection.
     *
     * @param tableName the name of the table to check
     * @param dbName the database file name
     * @return true if the table exists; false otherwise
     */
    private boolean tableExists(String tableName, String dbName) {
        String url = "jdbc:sqlite:" + dbName;
        try (Connection conn = DriverManager.getConnection(url);
             ResultSet rs = conn.getMetaData().getTables(null, null, tableName, null)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}