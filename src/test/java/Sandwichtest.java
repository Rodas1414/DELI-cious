import Delicious.mode1.Sandwich;
import Delicious.mode1.enums.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Sandwichtest {

    @Test
    void testBasicFourInchSandwich() {
        Sandwich sandwich = new Sandwich(Size.FOUR_INCH, Bread.WHITE);
        sandwich.addMeat(Meat.BACON, 1);
        sandwich.addCheese(Cheese.CHEDDAR, 1);
        sandwich.setToasted(true);

        System.out.println("Test 1:\n" + sandwich);
        System.out.printf("Price: $%.2f\n\n", sandwich.calculatePrice());

        double expectedPrice = sandwich.calculatePrice(); // Replace with actual expected price
        assertEquals(expectedPrice, sandwich.calculatePrice(), 0.01);
    }

    @Test
    void testEightInchSandwichWithToppings() {
        Sandwich sandwich = new Sandwich(Size.EIGHT_INCH, Bread.RYE);
        sandwich.addMeat(Meat.STEAK, 2);
        sandwich.addCheese(Cheese.PROVOLONE, 1);
        sandwich.addRegularTopping(RegularTopping.LETTUCE);
        sandwich.addSauce(Sauce.MAYO);
        sandwich.setToasted(false);

        System.out.println("Test 2:\n" + sandwich);
        System.out.printf("Price: $%.2f\n\n", sandwich.calculatePrice());

        double expectedPrice = sandwich.calculatePrice(); // Replace with actual expected price
        assertEquals(expectedPrice, sandwich.calculatePrice(), 0.01);
    }

    @Test
    void testTwelveInchLoadedSandwich() {
        Sandwich sandwich = new Sandwich(Size.TWELVE_INCH, Bread.WRAP);
        for (Meat meat : Meat.values()) sandwich.addMeat(meat, 1);
        for (Cheese cheese : Cheese.values()) sandwich.addCheese(cheese, 1);
        for (RegularTopping topping : RegularTopping.values()) sandwich.addRegularTopping(topping);
        for (Sauce sauce : Sauce.values()) sandwich.addSauce(sauce);
        sandwich.setToasted(true);

        System.out.println("Test 3:\n" + sandwich);
        System.out.printf("Price: $%.2f\n", sandwich.calculatePrice());

        double expectedPrice = sandwich.calculatePrice(); // Replace with actual expected price
        assertEquals(expectedPrice, sandwich.calculatePrice(), 0.01);
    }
}
