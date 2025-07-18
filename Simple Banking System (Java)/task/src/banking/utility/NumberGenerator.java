package banking.utility;

import java.util.Random;

/**
 * Utility class to generate random numeric strings of a given length.
 * Used for generating card numbers, PINS, etc.
 */
public class NumberGenerator {

    /**
     * Generate a random numeric string of exactly `digits` digits.
     * Each digit is between 0 and 9.
     *
     * @param digits the number of digits to generate
     * @return a numeric string of the given length
     */
    public static String generateRandomDigitString(int digits) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Append `digits` random digits (0-9)
        for (int i = 0; i < digits; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();

    }
}
