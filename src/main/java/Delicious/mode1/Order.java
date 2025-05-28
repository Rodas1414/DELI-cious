package Delicious.mode1;


import Delicious.mode1.ethiopian.Kitfo;
import Delicious.mode1.ethiopian.Tibs;

import java.time.LocalDateTime;
import java.util.*;

public class Order {
    private List<Sandwich> sandwiches = new ArrayList<>();
    private List<Drink> drinks = new ArrayList<>();
    private List<Chip> chips = new ArrayList<>();
    private LocalDateTime timestamp = LocalDateTime.now();
    private List<Tibs> TibsList = new ArrayList<>();
    private List<Kitfo> KitfoList = new ArrayList<>();


    public void addSandwich(Sandwich s) {
        sandwiches.add(s);
    }

    public void addDrink(Drink d) {
        drinks.add(d);
    }

    public void addChip(Chip c) {
        chips.add(c);
    }

    public void addTibs(Tibs tibs) {
        TibsList.add(tibs);
    }

    public void addKitfo(Kitfo kitfo) {
        KitfoList.add(kitfo);
    }

    public double getTotalPrice() {
        return sandwiches.stream().mapToDouble(Sandwich::calculatePrice).sum()
                + drinks.stream().mapToDouble(Drink::getPrice).sum()
                + chips.stream().mapToDouble(Chip::getPrice).sum();
    }

    public List<Sandwich> getSandwiches() {
        return sandwiches;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<Chip> getChips() {
        return chips;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }


    @Override
    public String toString() {
        return "Order: " + timestamp + "\nSandwiches: " + sandwiches +
                "\nDrinks: " + drinks + "\nChips: " + chips +
                "\nTotal: $" + getTotalPrice();
    }

    public Tibs[] getTibsList() {
        return TibsList.toArray(new Tibs[0]);
    }

    public Kitfo[] getKitfoList() {
        return KitfoList.toArray(new Kitfo[0]);
    }
}


