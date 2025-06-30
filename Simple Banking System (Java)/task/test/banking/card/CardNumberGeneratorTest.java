package banking.card;

import org.junit.Test;
import static org.junit.Assert.*;

public class CardNumberGeneratorTest {

    /**
     * Verifies the card start with the correct BIN number
     */
    @Test
    public void testCardStartWithBin() {
        String card = CardNumberGenerator.generateCardNumber();
        assertTrue("Card number should start with BIN 400000",
                card.startsWith("400000"));
    }
}