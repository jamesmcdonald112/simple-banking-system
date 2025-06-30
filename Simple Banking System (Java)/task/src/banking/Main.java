package banking;

import banking.utility.NumberGenerator;

public class Main {


    public static void main(String[] args) {

        String randomNumber = NumberGenerator.generateRandomDigitString(10000);

        System.out.printf(randomNumber);
    }
}
