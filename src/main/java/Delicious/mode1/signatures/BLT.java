package Delicious.mode1;

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
