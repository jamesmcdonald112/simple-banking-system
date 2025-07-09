package banking.database;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseConfigTest {

    /**
     * Verifies that a valid -fileName argument returns the correct file name.
     */
    @Test
    public void testParseDbFileName_ValidArgs_ReturnsFileName() {
        String[] args = {"-fileName", "bank.s3db"};
        assertEquals("File name should equal: " + args[1],
                "bank.s3db",
                DatabaseConfig.parseDbFileName(args));
    }

    /**
     * Verifies that an invalid argument returns null.
     */
    @Test
    public void testParseDbFileName_InvalidArgs_ReturnsNull() {
        String[] args = {"-invalidInput", "bank.s3db"};
        assertNull("should return null",
                DatabaseConfig.parseDbFileName(args));
    }
}