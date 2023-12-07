import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserLoginGUI extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton registerButton;

    public UserLoginGUI() {
        createUI();
    }

    private AuthenticationService authService = new AuthenticationService();

    private void createUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 5, 5));

        JLabel userLabel = new JLabel("Username:");
        add(userLabel);
        userField = new JTextField();
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        add(passLabel);
        passField = new JPasswordField();
        add(passField);

        loginButton = new JButton("Login");
        // Make sure that the authService is instantiated before adding the listener
        authService = new AuthenticationService(); 
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            char[] password = passField.getPassword();
            boolean authenticated = authService.authenticateUser(username, password);
            if(authenticated) {
                openDashboard();
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Authentication failed.");
            }
        });
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.addActionListener(e -> {
            // Registration logic here
            String username = userField.getText();
            char[] password = passField.getPassword();
            registerUser(username, password);
        });
        add(registerButton);
    }

    private void registerUser(String username, char[] password) {
        try {
            // Convert the password to a hash
            String hashedPassword = hashPassword(password);
            
            // Save the username and hashed password to a file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
                writer.write(username + ":" + hashedPassword);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "User registered successfully.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error writing to file.");
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(this, "Error hashing password.");
            e.printStackTrace();
        }
    }

    private String hashPassword(char[] password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(new String(password).getBytes(StandardCharsets.UTF_8));
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);
        
        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));
        
        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        
        return hexString.toString();
    }

    private void openDashboard() {
        SwingUtilities.invokeLater(() -> {
            DashboardUI dashboard = new DashboardUI();
            dashboard.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserLoginGUI().setVisible(true));
    }
}
