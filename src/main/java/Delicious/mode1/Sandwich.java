package Delicious.mode1;

import Delicious.mode1.enums.*;
import java.util.*;

public class Sandwich {
    private Size size;
    private Bread bread;
    private Map<Meat, Integer> meats = new HashMap<>();
    private Map<Cheese, Integer> cheeses = new HashMap<>();
    private List<RegularTopping> regularToppings = new ArrayList<>();
    private List<Sauce> sauces = new ArrayList<>();
    private boolean toasted;

    public Sandwich(Size size, Bread bread) {
        this.size = size;
        this.bread = bread;
    }

    public void addMeat(Meat meat, int count) {
        meats.put(meat, meats.getOrDefault(meat, 0) + count);
    }

    public void addCheese(Cheese cheese, int count) {
        cheeses.put(cheese, cheeses.getOrDefault(cheese, 0) + count);
    }

    public void addRegularTopping(RegularTopping topping) {
        regularToppings.add(topping);
    }

    public void addSauce(Sauce sauce) {
        sauces.add(sauce);
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public double calculatePrice() {
        double basePrice = 0;
        switch (size) {
            case FOUR_INCH -> basePrice = 5.50;
            case EIGHT_INCH -> basePrice = 7.00;
            case TWELVE_INCH -> basePrice = 8.50;
        }

        for (var entry : meats.entrySet()) {
            int count = entry.getValue();
            switch (size) {
                case FOUR_INCH -> basePrice += count * 1.00;
                case EIGHT_INCH -> basePrice += count * 2.00;
                case TWELVE_INCH -> basePrice += count * 3.00;
            }
        }

        for (var entry : cheeses.entrySet()) {
            int count = entry.getValue();
            switch (size) {
                case FOUR_INCH -> basePrice += count * 0.75;
                case EIGHT_INCH -> basePrice += count * 1.50;
                case TWELVE_INCH -> basePrice += count * 2.25;
            }
        }

        return basePrice;
    }

    public static Size getSizeFromInput(String input) {
        input = input.trim();
        if (input.equals("4") || input.equalsIgnoreCase("A")) return Size.FOUR_INCH;
        if (input.equals("8") || input.equalsIgnoreCase("B")) return Size.EIGHT_INCH;
        if (input.equals("12") || input.equals("3") || input.equalsIgnoreCase("C")) return Size.TWELVE_INCH;
        throw new IllegalArgumentException("Invalid input for sandwich size.");
    }


    public static Bread getBreadFromInput(String input) {
        return switch (input.toUpperCase()) {
            case "1", "A" -> Bread.WHITE;
            case "2", "B" -> Bread.WHEAT;
            case "3", "C" -> Bread.RYE;
            case "4", "D" -> Bread.WRAP;
            default -> throw new IllegalArgumentException("Invalid input for bread type.");
        };
    }

    @Override
    public String toString() {
        return size + " Sandwich on " + bread +
                ", Meats: " + meats +
                ", Cheeses: " + cheeses +
                ", Toppings: " + regularToppings +
                ", Sauces: " + sauces +
                ", Toasted: " + toasted;
    }
}