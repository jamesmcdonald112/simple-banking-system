package banking.card;

import banking.utility.NumberGenerator;

public class CardNumberGenerator {

    /**
     * Generates the 16 digit card number.
     *
     * @return 16-digit Card number as a String
     */
    public static String generateCardNumber() {
        StringBuilder sb = new StringBuilder();

        // Generate BIN and append
        sb.append(generateBin());

        // Generate customer account number and append
        sb.append(generateCustomerAccountNumber());

        // Generate checksum and append
        sb.append(generateChecksum());

        return sb.toString();
    }

    /**
     * Generates the BIN for the card
     *
     * @return The BIN number as a String
     */
    private static String generateBin() {
        return "400000";
    }

    /**
     * Generates a random customer account number
     *
     * @return the customer account number as a string
     */
    private static String generateCustomerAccountNumber() {
        return NumberGenerator.generateRandomDigitString(9);
    }

    /**
     * Generates the checksum for the customer account number
     *
     * @return the checksum as a string
     */
    private static String generateChecksum() {
        return "1";
    }
}
