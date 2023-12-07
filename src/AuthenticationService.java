import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthenticationService {

    public boolean authenticateUser(String username, char[] password) {
        // Hash the provided password
        String hashedPassword = hashPassword(password);

        // Open the users.txt file and search for the username and password hash
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String storedUsername = parts[0];
                    String storedPasswordHash = parts[1];
                    // Check if the stored username matches the provided username
                    if (storedUsername.equals(username)) {
                        // If the username matches, check if the password hashes match
                        return storedPasswordHash.equals(hashedPassword);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading from the users file.");
            e.printStackTrace();
        }
        // If no matching username is found, or if an exception occurs, return false
        return false;
    }

    public String hashPassword(char[] password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(new String(password).getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not hash password", e);
        }
    }
}
