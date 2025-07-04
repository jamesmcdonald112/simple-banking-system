package banking.runner;

import banking.menu.MenuApplication;

import java.util.Scanner;

public class Runner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuApplication menu = new MenuApplication(scanner);
        menu.start();
        scanner.close();
    }
}
