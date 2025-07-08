package banking.utility;

import java.util.Arrays;

public class LuhnUtils {

    public static void main(String[] args) {
        System.out.println(calculateChecksum("400000844943340"));
    }

    /**
     * Applies the Luhn algorithm to check whether a card number is valid.
     * The last digit is treated as a checksum and used to verify the preceding digits.
     *
     * @param cardNumber The card number to check as a String
     * @return True if valid; false otherwise
     */
    public static boolean isValid(String cardNumber) {
        int[] digits = convertCardAsStringToIntArray(cardNumber);

        int checksumDigit = digits[digits.length - 1];

        // Remove the checksum digit for calculation
        digits = Arrays.copyOf(digits, digits.length - 1);

        // Keep a running total
        int total = 0;

        // iterate through all numbers, double the odd digits, and add all to the total
        for (int i = 0; i < digits.length; i++) {
            int currentDigit = digits[i];

            // Double digits at even indexes (0-based)
            if (i % 2 == 0) currentDigit *= 2;

            // Subtract 9 from values greater than 9
            if(currentDigit > 9) currentDigit -= 9;

            total += currentDigit;
        }

        return (total + checksumDigit) % 10 == 0;
    }

    /**
     * Calculates the Luhn checksum digit for a given partial card number (usually BIN + account identifier).
     * <p>
     * The Luhn algorithm is used to validate identification numbers like credit card numbers. This method
     * implements the core algorithm to generate the final checksum digit that would make the full number valid.
     * </p>
     *
     * <p>
     * Algorithm steps:
     * <ul>
     *   <li>Convert the input string to an array of digits</li>
     *   <li>Double every digit at even index (0-based)</li>
     *   <li>If a doubled digit exceeds 9, subtract 9 from it</li>
     *   <li>Sum all the processed digits</li>
     *   <li>Calculate the checksum so that (sum + checksum) % 10 == 0</li>
     * </ul>
     * </p>
     *
     * @param accountIdentifierAndBin A string of 15 digits representing the card number without the checksum.
     * @return The checksum digit (0–9) that should be appended to make the full number valid.
     */
    public static int calculateChecksum(String accountIdentifierAndBin) {
        int[] digits = convertCardAsStringToIntArray(accountIdentifierAndBin);

        int total = 0;

        // iterate through all numbers, double the odd digits, and add all to the total
        for (int i = 0; i < digits.length; i++) {
            int currentDigit = digits[i];

            // Double digits at even indexes (0-based)
            if (i % 2 == 0) currentDigit *= 2;

            // Subtract 9 from values greater than 9
            if(currentDigit > 9) currentDigit -= 9;

            total += currentDigit;
        }

        int checksum = (10 -(total % 10)) % 10;
        return checksum;
    }

    /**
     * Converts a card number string into an array of its individual digits.
     *
     * @param cardNumber The credit card number as a string (e.g., "4000008449433403")
     * @return An array of integers representing each digit in the card number
     */
    private static int[] convertCardAsStringToIntArray(String cardNumber) {
        char[] chars = cardNumber.toCharArray();
        int[] digits = new int[chars.length];

        for (int i = 0; i < chars.length; i++) {
            digits[i] = Character.getNumericValue(chars[i]);
        }
        return digits;
    }
}


/*
- Take the original card number
- Drop the last digit (16th digit)
- Multiply odd digits (including the first digit) by 2
- Subtract 9 to numbers over 9
- Add all numbers together
- Whatever is left over (moduls 10) is the check sum

- The card number is stored as a string, i need to break each character into an array and
  convert it to an int for this to work. This will allow e to multiply by two and to add
  together. At the end, i can convert it back to a string
 */