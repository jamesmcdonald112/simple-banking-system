package banking.runner;

import banking.menu.main.MainMenuApplication;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainMenuApplication menu = new MainMenuApplication(scanner);
        menu.start();
        scanner.close();
    }
}
