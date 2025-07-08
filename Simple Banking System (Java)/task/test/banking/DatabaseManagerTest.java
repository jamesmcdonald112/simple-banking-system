package banking;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseManagerTest {

    /**
     * Tests that connecting to a valid database returns true.
     */
    @Test
    public void testConnectToValidDatabaseReturnsTrue() {
        boolean result = DatabaseManager.connectTo("test.s3db");
        assertTrue("Valid database should return true",
                result);
    }

    /**
     * Tests that connecting to an invalid database path returns false.
     */
    @Test
    public void testConnectToInvalidDatabaseReturnsFalse() {
        boolean result = DatabaseManager.connectTo("/invalid/path.db");
        assertFalse("Invalid database should return false",
                result);
    }
}