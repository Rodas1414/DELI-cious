package Delicious.ui;
import java.util.Scanner;

public class OrderScreen {
    private final Scanner scanner;

    public OrderScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public int show() {
        System.out.println("\n--- Order Menu ---");
        System.out.println("1) Add Sandwich");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Chips");
        System.out.println("4) Checkout");
        System.out.println("0) Cancel Order");
        System.out.print("Choose an option: ");
        return scanner.nextInt();
    }
}

