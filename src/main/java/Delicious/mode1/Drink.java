package Delicious.mode1;



import Delicious.mode1.enums.Size;

public class Drink {
    private Size size;
    private String flavor;

    public Drink(Size size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public double getPrice() {
        return switch (size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
            default -> 0;
        };
    }

    public Size getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

    @Override
    public String toString() {
        return size + " Drink - Flavor: " + flavor;
    }
}
