package banking.utility;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for Number Generator.
 * Verifies that generated values are digit-only, correct in length, and different on consecutive
 * calls.
 */
public class NumberGeneratorTest {

    private int length = 8;
    private int MAX_LENGTH = 19;
    private String result;

    // Runs before each test to initialise a default random result for shared use
    @Before
    public void setUp() {
        length = 8;
        result = NumberGenerator.generateRandomDigitString(length);
    }


    @Test
    public void testGeneratedLength() {
        assertEquals("Generated number should have a length of " + length + " digits long",
                length,
                result.length());
    }

    @Test
    public void testOnlyContainsDigits() {
        assertTrue("Expected only digits [\\d+], but got: " + result,
                result.matches("\\d+"));
    }

    @Test
    public void testResultIsNotNullOrBlank() {
        assertNotNull("Expected result to be not null, but got: " + result,
                result);
        assertFalse("Expected result to be not blank, but got: " + result,
                result.isBlank());
    }

    @Test
    public void testMinimumLength() {
        result = NumberGenerator.generateRandomDigitString(1);
        assertEquals("1 - digit should be length of 1",
                1,
                result.length());
    }

    @Test
    public void testMaximumLength() {
        result = NumberGenerator.generateRandomDigitString(MAX_LENGTH);
        assertEquals("Max length string should be " + MAX_LENGTH + " digits",
                MAX_LENGTH,
                result.length());
    }

    @Test
    public void testValuesAreNotRepeated() {
        String result2 = NumberGenerator.generateRandomDigitString(length);
        assertNotEquals("Consecutive calls should produce different results.\nResult 1: "
                        + result + "\nResult 2: " + result2 + "\n",
                result,
                result2);
    }

}