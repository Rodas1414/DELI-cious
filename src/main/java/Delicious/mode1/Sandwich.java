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
        double basePrice = switch (size) {
            case FOUR_INCH -> 5.50;
            case EIGHT_INCH -> 7.00;
            case TWELVE_INCH -> 8.50;
            default -> 0;
        };

        for (var entry : meats.entrySet()) {
            int count = entry.getValue();
            basePrice += switch (size) {
                case FOUR_INCH -> count * 1.00;
                case EIGHT_INCH -> count * 2.00;
                case TWELVE_INCH -> count * 3.00;
                default -> 0;
            };
        }

        for (var entry : cheeses.entrySet()) {
            int count = entry.getValue();
            basePrice += switch (size) {
                case FOUR_INCH -> count * 0.75;
                case EIGHT_INCH -> count * 1.50;
                case TWELVE_INCH -> count * 2.25;
                default -> 0;
            };
        }

        return basePrice;
    }

    @Override
    public String toString() {
        return size + " Sandwich on " + bread + ", Meats: " + meats +
                ", Cheeses: " + cheeses + ", Toppings: " + regularToppings +
                ", Sauces: " + sauces + ", Toasted: " + toasted;
    }
}
