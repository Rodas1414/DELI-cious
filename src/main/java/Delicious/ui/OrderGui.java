// === File: ui/OrderGUI.java ===
package Delicious.ui;

import Delicious.mode1.*;
import Delicious.mode1.enums.*;
import Delicious.mode1.ethiopian.*;
import Delicious.util.ReceiptGenerator;

import javax.swing.*;
import java.awt.*;

public class OrderGui {
    private static Order order = new Order();

    public static void show() {
        JFrame frame = new JFrame("New Order - DELI-cious POS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(41, 40, 39));

        JLabel title = new JLabel("Build Your Order 🍴");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(new Color(85, 37, 130));

        JButton sandwichBtn = createStyledButton("🥪 Add Sandwich", new Color(56, 142, 60));
        JButton drinkBtn = createStyledButton("🥤 Add Drink", new Color(2, 136, 209));
        JButton chipBtn = createStyledButton("🍟 Add Chips", new Color(255, 160, 0));
        JButton tibsBtn = createStyledButton("🍲 Add Tibs", new Color(153, 76, 0));
        JButton kitfoBtn = createStyledButton("🥩 Add Kitfo", new Color(102, 0, 51));
        JButton checkoutBtn = createStyledButton("💰 Checkout", new Color(194, 24, 91));

        sandwichBtn.addActionListener(e -> addSandwich(frame));
        drinkBtn.addActionListener(e -> addDrink(frame));
        chipBtn.addActionListener(e -> addChips(frame));
        tibsBtn.addActionListener(e -> addTibs(frame));
        kitfoBtn.addActionListener(e -> addKitfo(frame));
        checkoutBtn.addActionListener(e -> showReceipt(frame));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        panel.add(sandwichBtn, gbc);
        gbc.gridx++;
        panel.add(drinkBtn, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(chipBtn, gbc);
        gbc.gridx++;
        panel.add(tibsBtn, gbc);

        gbc.gridx = 0; gbc.gridy++;
        panel.add(kitfoBtn, gbc);
        gbc.gridx++;
        panel.add(checkoutBtn, gbc);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JButton createStyledButton(String text, Color bg) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(220, 50));
        button.setFont(new Font("SansSerif", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(bg);
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 50)));
        return button;
    }

    private static void addSandwich(JFrame parent) {
        String[] sizeOptions = {"4 inch", "8 inch", "12 inch"};
        String[] breadOptions = {"WHITE", "WHEAT", "RYE", "WRAP"};

        int sizeChoice = JOptionPane.showOptionDialog(parent, "Choose sandwich size:", "Sandwich Size",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, sizeOptions, sizeOptions[1]);

        int breadChoice = JOptionPane.showOptionDialog(parent, "Choose bread:", "Bread Type",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, breadOptions, breadOptions[0]);

        Size size = switch (sizeChoice) {
            case 0 -> Size.FOUR_INCH;
            case 1 -> Size.EIGHT_INCH;
            case 2 -> Size.TWELVE_INCH;
            default -> Size.EIGHT_INCH;
        };

        Bread bread = Bread.valueOf(breadOptions[breadChoice]);

        Sandwich sandwich = new Sandwich(size, bread);

        int toastChoice = JOptionPane.showConfirmDialog(parent,
                "Would you like the sandwich toasted?",
                "Toasted Option",
                JOptionPane.YES_NO_OPTION);
        boolean toasted = toastChoice == JOptionPane.YES_OPTION;

        sandwich.setToasted(toasted);
        sandwich.addMeat(Meat.BACON, 1);
        sandwich.addCheese(Cheese.CHEDDAR, 1);
        sandwich.addRegularTopping(RegularTopping.LETTUCE);
        sandwich.addSauce(Sauce.RANCH);

        order.addSandwich(sandwich);
        JOptionPane.showMessageDialog(parent, "Added: " + sandwich +
                "\nPrice: $" + String.format("%.2f", sandwich.calculatePrice()));
    }

    private static void addDrink(JFrame parent) {
        String[] sizeOptions = {"SMALL", "MEDIUM", "LARGE"};
        String sizeStr = (String) JOptionPane.showInputDialog(parent, "Select drink size:", "Drink Size",
                JOptionPane.PLAIN_MESSAGE, null, sizeOptions, sizeOptions[1]);

        if (sizeStr == null) return;

        Size size = Size.valueOf(sizeStr);
        String flavor = JOptionPane.showInputDialog(parent, "Enter flavor:", "Cola");
        if (flavor == null || flavor.isBlank()) return;

        Drink drink = new Drink(size, flavor);
        order.addDrink(drink);
        JOptionPane.showMessageDialog(parent, "Added: " + drink +
                "\nPrice: $" + String.format("%.2f", drink.getPrice()));
    }

    private static void addChips(JFrame parent) {
        String[] sizeOptions = {"SMALL", "MEDIUM", "LARGE"};
        String sizeStr = (String) JOptionPane.showInputDialog(parent, "Select chip size:", "Chip Size",
                JOptionPane.PLAIN_MESSAGE, null, sizeOptions, sizeOptions[0]);

        if (sizeStr == null) return;

        Size size = Size.valueOf(sizeStr);
        String type = JOptionPane.showInputDialog(parent, "Enter chip type:", "Doritos");
        if (type == null || type.isBlank()) return;

        Chip chip = new Chip(type, size);
        order.addChip(chip);
        JOptionPane.showMessageDialog(parent, "Added: " + chip +
                "\nPrice: $" + String.format("%.2f", chip.getPrice()));
    }

    private static void addKitfo(JFrame frame) {
        String[] cookOptions = {"MEDIUM", "MEDIUM_RARE", "WELL_DONE"};
        String cookChoice = (String) JOptionPane.showInputDialog(frame,
                "How would you like your Kitfo cooked?", "Kitfo Cook Level",
                JOptionPane.PLAIN_MESSAGE, null, cookOptions, cookOptions[0]);

        if (cookChoice == null) return;

        boolean hasOnion = JOptionPane.showConfirmDialog(frame,
                "Would you like onion?", "Onion Option",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        boolean hasJalapeno = JOptionPane.showConfirmDialog(frame,
                "Would you like jalapeño (halopino)?", "Spice Option",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        boolean withEnjera = JOptionPane.showConfirmDialog(frame,
                "Would you like Enjera on the side?", "Side Option",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        Kitfo.CookLevel cookLevel = Kitfo.CookLevel.valueOf(cookChoice);
        Kitfo kitfo = new Kitfo(cookLevel, hasOnion, hasJalapeno, withEnjera);
        order.addKitfo(kitfo);

        JOptionPane.showMessageDialog(frame,
                "Added: " + kitfo + "\nPrice: $" + String.format("%.2f", kitfo.getPrice()));
    }

    private static void addTibs(JFrame frame) {
        int rawChoice = JOptionPane.showConfirmDialog(frame,
                "Would you like your Tibs raw?", "Cook Style",
                JOptionPane.YES_NO_OPTION);
        if (rawChoice == JOptionPane.CLOSED_OPTION) return;

        boolean isRaw = (rawChoice == JOptionPane.YES_OPTION);

        boolean hasButter = JOptionPane.showConfirmDialog(frame,
                "Would you like to add butter?", "Butter Option",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        boolean withOnion = JOptionPane.showConfirmDialog(frame,
                "Would you like onion?", "Onion Option",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        String[] spiceOptions = {"MILD", "MEDIUM", "SPICY"};
        String spiceChoice = (String) JOptionPane.showInputDialog(frame,
                "Select spiciness level:", "Spice Level",
                JOptionPane.PLAIN_MESSAGE, null, spiceOptions, spiceOptions[1]);
        if (spiceChoice == null) return;

        boolean withAwaze = JOptionPane.showConfirmDialog(frame,
                "Add Awaze as a side?", "Side Option",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        boolean withMitmita = JOptionPane.showConfirmDialog(frame,
                "Add Mitmita as a side?", "Side Option",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        Tibs.Spiciness spiceLevel = Tibs.Spiciness.valueOf(spiceChoice);
        Tibs tibs = new Tibs(isRaw, hasButter, withOnion, spiceLevel, withAwaze, withMitmita);
        order.addTibs(tibs);

        JOptionPane.showMessageDialog(frame,
                "Added: " + tibs + "\nPrice: $" + String.format("%.2f", tibs.getPrice()));
    }

    private static void showReceipt(JFrame frame) {
        StringBuilder sb = new StringBuilder();
        double total = 0;

        for (Sandwich s : order.getSandwiches()) {
            sb.append(s).append("\n-> $").append(String.format("%.2f", s.calculatePrice())).append("\n\n");
            total += s.calculatePrice();
        }
        for (Drink d : order.getDrinks()) {
            sb.append(d).append("\n-> $").append(String.format("%.2f", d.getPrice())).append("\n\n");
            total += d.getPrice();
        }
        for (Chip c : order.getChips()) {
            sb.append(c).append("\n-> $").append(String.format("%.2f", c.getPrice())).append("\n\n");
            total += c.getPrice();
        }
        for (Tibs t : order.getTibsList()) {
            sb.append(t).append("\n-> $").append(String.format("%.2f", t.getPrice())).append("\n\n");
            total += t.getPrice();
        }
        for (Kitfo k : order.getKitfoList()) {
            sb.append(k).append("\n-> $").append(String.format("%.2f", k.getPrice())).append("\n\n");
            total += k.getPrice();
        }

        sb.append("TOTAL: $").append(String.format("%.2f", total));
        JOptionPane.showMessageDialog(frame, sb.toString());
        ReceiptGenerator.generate(order);
        frame.dispose();
    }
}
