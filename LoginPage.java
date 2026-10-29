package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class LoginPage extends JFrame {

    private JTextField pinField;

    public LoginPage() {
        setTitle("Check Courier Availability");
        setSize(450, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));

        JLabel label = new JLabel("Enter 6-digit Pincode:", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));

        pinField = new JTextField();
        pinField.setHorizontalAlignment(JTextField.CENTER);
        pinField.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton checkBtn = new JButton("Check Availability");
        checkBtn.setBackground(new Color(0, 102, 204));
        checkBtn.setForeground(Color.WHITE);
        checkBtn.setFocusPainted(false);

        JButton backBtn = new JButton("Back");

        add(label);
        add(pinField);
        add(checkBtn);
        add(backBtn);

        checkBtn.addActionListener(e -> {
            String pin = pinField.getText().trim();
            if (!pin.matches("\\d{6}")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid 6-digit pincode.");
                return;
            }
            checkCourierAvailability(pin);
        });

        backBtn.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });
    }

    private void checkCourierAvailability(String pin) {
        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                JOptionPane.showMessageDialog(this, "Database connection failed!",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String query = "SELECT place, services FROM courierservice WHERE pin_code = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, pin);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String place = rs.getString("place");
                String services = rs.getString("services");
                JOptionPane.showMessageDialog(this,
                        "Courier services available in " + place + ":\n" + services);
            } else {
                JOptionPane.showMessageDialog(this, "Courier service NOT available");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
}
