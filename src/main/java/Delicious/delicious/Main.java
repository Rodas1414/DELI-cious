
package Delicious.delicious;

import Delicious.mode1.*;
import Delicious.mode1.enums.*;
import Delicious.ui.HomeScreen;
import Delicious.ui.OrderScreen;
import Delicious.util.ReceiptGenerator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HomeScreen homeScreen = new HomeScreen(scanner);
        OrderScreen orderScreen = new OrderScreen(scanner);

        while (true) {
            int choice = homeScreen.show();
            if (choice == 0) break;

            Order order = new Order();
            boolean ordering = true;
            while (ordering) {
                int orderChoice = orderScreen.show();
                switch (orderChoice) {
                    case 1 -> {
                        System.out.print("Choose sandwich size (4=FOUR_INCH, 8=EIGHT_INCH, 12=TWELVE_INCH): ");
                        int sizeInput = scanner.nextInt();
                        Size size = switch (sizeInput) {
                            case 4 -> Size.FOUR_INCH;
                            case 8 -> Size.EIGHT_INCH;
                            case 12 -> Size.TWELVE_INCH;
                            default -> Size.EIGHT_INCH;
                        };

                        System.out.print("Choose bread (1=WHITE, 2=WHEAT, 3=RYE, 4=WRAP): ");
                        int breadInput = scanner.nextInt();
                        Bread bread = switch (breadInput) {
                            case 1 -> Bread.WHITE;
                            case 2 -> Bread.WHEAT;
                            case 3 -> Bread.RYE;
                            case 4 -> Bread.WRAP;
                            default -> Bread.WHITE;
                        };

                        Sandwich sandwich = new Sandwich(size, bread);

                        System.out.print("Add bacon? (y/n): ");
                        if (scanner.next().equalsIgnoreCase("y")) sandwich.addMeat(Meat.BACON, 1);

                        System.out.print("Add cheddar cheese? (y/n): ");
                        if (scanner.next().equalsIgnoreCase("y")) sandwich.addCheese(Cheese.CHEDDAR, 1);

                        System.out.print("Add lettuce? (y/n): ");
                        if (scanner.next().equalsIgnoreCase("y")) sandwich.addRegularTopping(RegularTopping.LETTUCE);

                        System.out.print("Add ranch sauce? (y/n): ");
                        if (scanner.next().equalsIgnoreCase("y")) sandwich.addSauce(Sauce.RANCH);

                        System.out.print("Toast the sandwich? (y/n): ");
                        sandwich.setToasted(scanner.next().equalsIgnoreCase("y"));

                        order.addSandwich(sandwich);
                        System.out.println("Custom sandwich added!");
                    }
                    case 2 -> {
                        System.out.print("Choose drink size (1=SMALL, 2=MEDIUM, 3=LARGE): ");
                        int drinkSize = scanner.nextInt();
                        Size size = switch (drinkSize) {
                            case 1 -> Size.SMALL;
                            case 2 -> Size.MEDIUM;
                            case 3 -> Size.LARGE;
                            default -> Size.MEDIUM;
                        };

                        System.out.print("Enter drink flavor: ");
                        scanner.nextLine(); // consume leftover newline
                        String flavor = scanner.nextLine();

                        Drink drink = new Drink(size, flavor);
                        order.addDrink(drink);
                        System.out.println("Drink added!");
                    }
                    case 3 -> {
                        System.out.print("Choose chip size (1=SMALL, 2=MEDIUM, 3=LARGE): ");
                        int chipSizeInput = scanner.nextInt();
                        Size size = switch (chipSizeInput) {
                            case 1 -> Size.SMALL;
                            case 2 -> Size.MEDIUM;
                            case 3 -> Size.LARGE;
                            default -> Size.MEDIUM;
                        };

                        System.out.print("Enter chip type (e.g. Lays, Doritos): ");
                        scanner.nextLine(); // consume leftover newline
                        String type = scanner.nextLine();

                        Chip chip = new Chip(type, size);
                        order.addChip(chip);
                        System.out.println("Chips added!");
                    }
                    case 4 -> {
                        System.out.println(" --- Checkout ---");

                                System.out.println(" Sandwiches:");
                        for (Sandwich s : order.getSandwiches()) {
                            System.out.println(s);
                            System.out.printf("-> Sandwich Price: $%.2f ", s.calculatePrice());
                        }

                        System.out.println("Drinks:");
                        for (Drink d : order.getDrinks()) {
                            System.out.println(d);
                            System.out.printf("-> Drink Price: $%.2f ", d.getPrice());
                        }

                        System.out.println("Chips:");
                        for (Chip c : order.getChips()) {
                            System.out.println(c);
                            System.out.printf("-> Chips Price: $%.2f ", c.getPrice());
                        }

                        System.out.printf("Total Price: $%.2f", order.getTotalPrice());
                                ReceiptGenerator.generate(order);
                        ordering = false;
                    }
                    case 0 -> {
                        System.out.println("Order canceled.");
                        ordering = false;
                    }
                    default -> System.out.println("Invalid choice.");
                }
            }
        }
        scanner.close();
        System.out.println("Goodbye!");
    }
}
