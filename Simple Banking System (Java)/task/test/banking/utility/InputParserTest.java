package banking.utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputParserTest {

    @Test
    public void testValidIntegerInput() {
        assertEquals("Should parse valid integer string", 42, InputParser.safeParseInt("42", -1));
    }

    @Test
    public void testInvalidIntegerInputReturnsFallback() {
        assertEquals("Should return fallback on invalid input", -1, InputParser.safeParseInt("notANumber", -1));
    }

    @Test
    public void testEmptyInputReturnsFallback() {
        assertEquals("Should return fallback on empty input", 0, InputParser.safeParseInt("", 0));
    }

    @Test
    public void testNullInputReturnsFallback() {
        assertEquals("Should return fallback on null input", 99, InputParser.safeParseInt(null, 99));
    }
}