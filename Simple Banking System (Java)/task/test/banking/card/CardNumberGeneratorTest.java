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

    /**
     * Verifies the card is 16-digits long and only numeric characters
     */
    @Test
    public void testCardLengthIs16Digits(){
        String card = CardNumberGenerator.generateCardNumber();
        assertTrue("Card only contains numeric characters. Card characters: " + card,
                card.matches("\\d+"));

        assertEquals("Card number should be 16 digits.",
                16,
                card.length());
    }
}