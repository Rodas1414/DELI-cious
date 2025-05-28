package Delicious.Test;


import Delicious.mode1.*;
import Delicious.mode1.enums.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    public void testAddSandwichAndTotal() {
        Order order = new Order();
        Sandwich sandwich = new Sandwich(Size.EIGHT_INCH, Bread.WHITE);
        sandwich.addMeat(Meat.STEAK, 1);
        sandwich.addCheese(Cheese.CHEDDAR, 1);
        sandwich.setToasted(true);
        order.addSandwich(sandwich);

        double expected = 7.00 + 2.00 + 1.50; // base + meat + cheese
        assertEquals(expected, order.getTotalPrice(), 0.001);
    }

    @Test
    public void testAddDrinkAndChip() {
        Order order = new Order();
        Drink drink = new Drink(Size.SMALL, "Lemonade");
        Chip chip = new Chip("Lays", Size.LARGE);

        order.addDrink(drink);
        order.addChip(chip);

        double expected = 2.00 + 2.50;
        assertEquals(expected, order.getTotalPrice(), 0.001);
    }

    @Test
    public void testEmptyOrderTotalIsZero() {
        Order order = new Order();
        assertEquals(0.0, order.getTotalPrice(), 0.001);
    }

    @Test
    public void testSandwichDescription() {
        Sandwich s = new Sandwich(Size.FOUR_INCH, Bread.WRAP);
        s.addMeat(Meat.HAM, 1);
        s.addRegularTopping(RegularTopping.TOMATOES);
        s.addSauce(Sauce.MAYO);
        s.setToasted(false);

        String desc = s.toString();
        assertTrue(desc.contains("FOUR_INCH"));
        assertTrue(desc.contains("WRAP"));
        assertTrue(desc.contains("HAM"));
        assertTrue(desc.contains("TOMATOES"));
        assertTrue(desc.contains("MAYO"));
    }
}
