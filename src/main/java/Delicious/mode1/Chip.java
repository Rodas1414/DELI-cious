package Delicious.mode1;

import Delicious.mode1.enums.Size;

public class Chip {
    private String type;
    private Size size;

    public Chip(String type, Size size) {
        this.type = type;
        this.size = size;
    }

    public double getPrice() {
        return switch (size) {
            case SMALL -> 1.50;
            case MEDIUM -> 2.00;
            case LARGE -> 2.50;
            default -> 1.50;
        };
    }

    public Size getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return size + " Chips - " + type;
    }
}
