package banking.database;

import org.junit.After;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class DatabaseManagerTest {

    @After
    public void cleanup() {
        new File("DatabaseManagerTest.s3db").delete();
    }

    /**
     * Tests that connecting to a valid database returns true.
     */
    @Test
    public void testConnectToValidDatabaseReturnsTrue() {
        DatabaseManager manager = new DatabaseManager();
        boolean result = manager.connectTo("DatabaseManagerTest.s3db");
        assertTrue("Valid database should return true",
                result);

    }

    /**
     * Tests that connecting to an invalid database path returns false.
     */
    @Test
    public void testConnectToInvalidDatabaseReturnsFalse() {
        DatabaseManager manager = new DatabaseManager();
        boolean result = manager.connectTo("/invalid/path.db");
        assertFalse("Invalid database should return false",
                result);
    }
}