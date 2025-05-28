package Delicious.mode1.signatures;

import Delicious.mode1.Sandwich;
import Delicious.mode1.enums.*;

public class BLT extends Sandwich {
    public BLT() {
        super(Size.EIGHT_INCH, Bread.WHITE);
        addMeat(Meat.BACON, 1);
        addCheese(Cheese.CHEDDAR, 1);
        addRegularTopping(RegularTopping.LETTUCE);
        addRegularTopping(RegularTopping.TOMATOES);
        addSauce(Sauce.RANCH);
        setToasted(true);
    }

    @Override
    public String toString() {
        return "Signature Sandwich: BLT\n" + super.toString();
    }
}

