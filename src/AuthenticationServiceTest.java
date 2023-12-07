import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AuthenticationServiceTest {

    private AuthenticationService authService;

    @Before
    public void setUp() {
        authService = new AuthenticationService();
        // Normally, you would set up a mock data source or a test file with known contents.
        // For this example, create a 'users.txt' file in the root of your project with test data.
    }
    
    @Test
    public void testAuthenticateUser_InvalidPassword() {
        // Assuming 'users.txt' contains a line for the username "testUser"
        boolean result = authService.authenticateUser("testUser", "wrongPassword".toCharArray());
        assertFalse(result);
    }

    @Test
    public void testAuthenticateUser_NonExistingUser() {
        boolean result = authService.authenticateUser("nonExistingUser", "password".toCharArray());
        assertFalse(result);
    }
}
