package Delicious.mode1.ethiopian;



public class Kitfo {
    public enum CookLevel { MEDIUM, MEDIUM_RARE, WELL_DONE }

    private CookLevel cookLevel;
    private boolean hasOnion;
    private boolean hasJalapeno;
    private boolean withEnjera;

    public Kitfo(CookLevel cookLevel, boolean hasOnion, boolean hasJalapeno, boolean withEnjera) {
        this.cookLevel = cookLevel;
        this.hasOnion = hasOnion;
        this.hasJalapeno = hasJalapeno;
        this.withEnjera = withEnjera;
    }

    public double getPrice() {
        double price = 12.99;
        if (withEnjera) price += 2.00;
        return price;
    }

    @Override
    public String toString() {
        return "Kitfo (" + cookLevel +
                (hasOnion ? ", with onion" : "") +
                (hasJalapeno ? ", with jalapeño" : "") +
                (withEnjera ? ", served with Enjera" : "") +
                ")";
    }
}
