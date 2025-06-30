package banking.card;

public class CardNumberGenerator {

    /**
     * Generates the 16 digit card number.
     *
     * @return 16-digit Card number as a String
     */
    public static String generateCardNumber() {
        StringBuilder sb = new StringBuilder();

        // Generate BIN
        sb.append(generateBin());
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

}
