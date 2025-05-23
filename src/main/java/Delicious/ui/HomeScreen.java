package Delicious.ui;

import java.util.Scanner;

public class HomeScreen {
    private final Scanner scanner;

    public HomeScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public int show() {
        System.out.println("\n=== DELI-cious POS ===");
        System.out.println("1) New Order");
        System.out.println("0) Exit");
        System.out.print("Choose an option: ");
        return scanner.nextInt();
    }
}