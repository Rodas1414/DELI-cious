package Delicious.ui;
import javax.swing.*;
import java.awt.*;

public class HomeGui {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("DELI-cious POS");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);

            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            panel.setBackground(new Color(255, 245, 230));

            JLabel title = new JLabel("Welcome to DELI-cious POS");
            title.setFont(new Font("SansSerif", Font.BOLD, 24));
            title.setForeground(new Color(177, 53, 82));

            JButton newOrderButton = new JButton(" New Order");
            JButton exitButton = new JButton(" Exit");

            newOrderButton.setPreferredSize(new Dimension(200, 40));
            exitButton.setPreferredSize(new Dimension(200, 40));

            newOrderButton.setBackground(new Color(102, 187, 106));
            newOrderButton.setForeground(Color.black);
            exitButton.setBackground(new Color(239, 83, 80));
            exitButton.setForeground(Color.black);

            // ✅ Launch the real order screen
            newOrderButton.addActionListener(e -> {
                frame.dispose(); // Optional: close home screen
                OrderGui.show(); // Launch order GUI
            });

            exitButton.addActionListener(e -> frame.dispose());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(title, gbc);
            gbc.gridy++;
            panel.add(newOrderButton, gbc);
            gbc.gridy++;
            panel.add(exitButton, gbc);

            frame.add(panel);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
