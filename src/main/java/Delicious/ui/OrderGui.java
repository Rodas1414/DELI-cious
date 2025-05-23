package Delicious.ui;

import Delicious.mode1.*;
import Delicious.mode1.enums.*;
import Delicious.util.ReceiptGenerator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class OrderGui {
    private static Order order = new Order();

    public static void show() {
        JFrame frame = new JFrame("New Order - DELI-cious POS");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 250, 240));

        JLabel title = new JLabel("Build Your Order 🍴");
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(new Color(85, 37, 130));

        JButton sandwichBtn = createStyledButton("🥪 Add Sandwich", new Color(24, 27, 25));
        JButton drinkBtn = createStyledButton("🥤 Add Drink", new Color(25, 26, 27));
        JButton chipBtn = createStyledButton("🍟 Add Chips", new Color(46, 44, 41));
        JButton checkoutBtn = createStyledButton("💰 Checkout", new Color(37, 35, 36));

        sandwichBtn.addActionListener(e -> addSandwich(frame));
        drinkBtn.addActionListener(e -> addDrink(frame));
        chipBtn.addActionListener(e -> addChips(frame));
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
        sandwich.addMeat(Meat.BACON, 1);
        sandwich.addCheese(Cheese.CHEDDAR, 1);
        sandwich.addRegularTopping(RegularTopping.LETTUCE);
        sandwich.addSauce(Sauce.RANCH);
        sandwich.setToasted(true);

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

        sb.append("TOTAL: $").append(String.format("%.2f", total));
        JOptionPane.showMessageDialog(frame, sb.toString());
        ReceiptGenerator.generate(order);
        frame.dispose();
    }
}
