package ui;

import javax.swing.*;
import java.awt.*;

public class HomePage extends JFrame {

    public HomePage() {
        setTitle("Courier Availability Checker");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 20));

        JLabel title = new JLabel("Courier Availability Checker", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 26));
        title.setForeground(new Color(0, 102, 204));

        JLabel desc = new JLabel(
                "<html><center>Welcome to Courier Availability Checker!<br>" +
                "Check if courier services are available in your area with just a pincode.</center></html>",
                SwingConstants.CENTER);
        desc.setFont(new Font("Arial", Font.PLAIN, 16));
        desc.setForeground(Color.DARK_GRAY);

        JButton continueBtn = new JButton("Continue ->");
        continueBtn.setFont(new Font("Arial", Font.BOLD, 16));
        continueBtn.setBackground(new Color(0, 153, 76));
        continueBtn.setForeground(Color.WHITE);
        continueBtn.setFocusPainted(false);
        continueBtn.addActionListener(e -> {
            dispose();
            new LoginPage().setVisible(true);
        });

        JPanel center = new JPanel(new GridLayout(2, 1, 10, 10));
        center.add(title);
        center.add(desc);

        JPanel bottom = new JPanel();
        bottom.add(continueBtn);

        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}
