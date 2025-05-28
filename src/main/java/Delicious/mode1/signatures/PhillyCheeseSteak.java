package Delicious.mode1.signatures;

import Delicious.mode1.Sandwich;

public class PhillyCheeseSteak extends Sandwich {
    public PhillyCheeseSteak() {
        super(Size.EIGHT_INCH, Bread.WHITE);
        addMeat(Meat.STEAK, 1);
        addCheese(Cheese.AMERICAN, 1);
        addRegularTopping(RegularTopping.PEPPERS);
        addSauce(Sauce.MAYO);
        setToasted(true);
    }

    @Override
    public String toString() {
        return "Signature Sandwich: Philly Cheese Steak\n" + super.toString();
    }
}

