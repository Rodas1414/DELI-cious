package Delicious.mode1.ethiopian;



public class Tibs {
    public enum Spiciness { MILD, MEDIUM, SPICY }

    private boolean isRaw;
    private boolean hasButter;
    private boolean withOnion;
    private Spiciness spiceLevel;
    private boolean withAwaze;
    private boolean withMitmita;

    public Tibs(boolean isRaw, boolean hasButter, boolean withOnion,
                Spiciness spiceLevel, boolean withAwaze, boolean withMitmita) {
        this.isRaw = isRaw;
        this.hasButter = hasButter;
        this.withOnion = withOnion;
        this.spiceLevel = spiceLevel;
        this.withAwaze = withAwaze;
        this.withMitmita = withMitmita;
    }

    public double getPrice() {
        double base = isRaw ? 11.99 : 13.49;
        if (withAwaze) base += 1.00;
        if (withMitmita) base += 0.75;
        return base;
    }

    @Override
    public String toString() {
        return (isRaw ? "Raw" : "Cooked") + " Tibs" +
                (withOnion ? " with Onion" : "") +
                (hasButter ? ", with Butter" : "") +
                ", Spice: " + spiceLevel +
                (withAwaze ? ", with Awaze" : "") +
                (withMitmita ? ", with Mitmita" : "");
    }
}
