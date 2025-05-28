package Delicious.delicious;

import Delicious.mode1.*;
import Delicious.mode1.enums.*;
import Delicious.mode1.signatures.BLT;
import Delicious.mode1.signatures.PhillyCheeseSteak;
import Delicious.util.ReceiptGenerator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to DELI-cious!");
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            int choice = scanner.nextInt();

            if (choice == 0) break;

            Order order = new Order();
            boolean ordering = true;
            while (ordering) {
                System.out.println("\n--- Order Menu ---");
                System.out.println("1) Add Sandwich");
                System.out.println("2) Add Drink");
                System.out.println("3) Add Chips");
                System.out.println("4) Checkout");
                System.out.println("0) Cancel Order");
                int orderChoice = scanner.nextInt();

                switch (orderChoice) {
                    case 1 -> {
                        System.out.println("Select sandwich type:");
                        System.out.println("1) Custom Sandwich");
                        System.out.println("2) BLT");
                        System.out.println("3) Philly Cheese Steak");
                        int typeChoice = scanner.nextInt();
                        Sandwich sandwich;

                        if (typeChoice == 2) {
                            sandwich = new BLT();
                        } else if (typeChoice == 3) {
                            sandwich = new PhillyCheeseSteak();
                        } else {
                            System.out.print("Choose sandwich size (4, 8, 12): ");
                            Size size = Sandwich.getSizeFromInput(scanner.next());

                            System.out.print("Choose bread type (1=WHITE, 2=WHEAT, 3=RYE, 4=WRAP): ");
                            Bread bread = Sandwich.getBreadFromInput(scanner.next());
                            sandwich = new Sandwich(size, bread);

                            System.out.println("Add meats:");
                            for (Meat meat : Meat.values()) {
                                System.out.print("Add " + meat + "? (y/n): ");
                                if (scanner.next().equalsIgnoreCase("y")) {
                                    System.out.print("How many portions?: ");
                                    int count = scanner.nextInt();
                                    sandwich.addMeat(meat, count);
                                }
                            }

                            System.out.println("Add cheeses:");
                            for (Cheese cheese : Cheese.values()) {
                                System.out.print("Add " + cheese + "? (y/n): ");
                                if (scanner.next().equalsIgnoreCase("y")) {
                                    System.out.print("How many portions?: ");
                                    int count = scanner.nextInt();
                                    sandwich.addCheese(cheese, count);
                                }
                            }

                            System.out.println("Add toppings:");
                            for (RegularTopping topping : RegularTopping.values()) {
                                System.out.print("Add " + topping + "? (y/n): ");
                                if (scanner.next().equalsIgnoreCase("y")) {
                                    sandwich.addRegularTopping(topping);
                                }
                            }

                            System.out.println("Add sauces:");
                            for (Sauce sauce : Sauce.values()) {
                                System.out.print("Add " + sauce + "? (y/n): ");
                                if (scanner.next().equalsIgnoreCase("y")) {
                                    sandwich.addSauce(sauce);
                                }
                            }

                            System.out.print("Toast the sandwich? (y/n): ");
                            sandwich.setToasted(scanner.next().equalsIgnoreCase("y"));
                        }

                        order.addSandwich(sandwich);
                        System.out.println("Sandwich added!");
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
                        scanner.nextLine();
                        String flavor = scanner.nextLine();

                        order.addDrink(new Drink(size, flavor));
                        System.out.println("Drink added!");
                    }
                    case 3 -> {
                        System.out.print("Choose chip size (1=SMALL, 2=MEDIUM, 3=LARGE): ");
                        int chipSize = scanner.nextInt();
                        Size size = switch (chipSize) {
                            case 1 -> Size.SMALL;
                            case 2 -> Size.MEDIUM;
                            case 3 -> Size.LARGE;
                            default -> Size.MEDIUM;
                        };

                        System.out.print("Enter chip type: ");
                        scanner.nextLine();
                        String type = scanner.nextLine();

                        order.addChip(new Chip(type, size));
                        System.out.println("Chips added!");
                    }
                    case 4 -> {
                        System.out.println("--- Order Summary ---");
                        order.getSandwiches().forEach(System.out::println);
                        order.getDrinks().forEach(System.out::println);
                        order.getChips().forEach(System.out::println);
                        System.out.printf("Total: $%.2f\n", order.getTotalPrice());
                        ReceiptGenerator.generate(order);
                        ordering = false;
                    }
                    case 0 -> {
                        System.out.println("Order cancelled.");
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
