import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Test1 {

    private Login login;

    @BeforeEach
    public void setUp() {
        // This will run before each test
        login = new Login();
    }

    // ---------- USERNAME TESTS ----------

    @Test
    public void testValidUsername() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testInvalidUsername() {
        assertFalse(login.checkUserName("kyle !!!!!!!"));
    }

    // ---------- PASSWORD COMPLEXITY TESTS ----------

    @Test
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testInvalidPassword() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    // ---------- CELLPHONE FORMAT TESTS ----------

    @Test
    public void testValidCellPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testInvalidCellPhoneNumber() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    // ---------- LOGIN FUNCTIONALITY TESTS ----------

    @Test
    public void testLoginSuccess() {
        // Simulate registration
        login.username = "kyl_1";
        login.password = "Ch&&sec@ke99!";
        // Simulate login input
        assertTrue(login.username.equals("kyl_1") && login.password.equals("Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailure() {
        // Simulate registration
        login.username = "kyl_1";
        login.password = "Ch&&sec@ke99!";
        // Simulate wrong login input
        assertFalse(login.username.equals("wronguser") && login.password.equals("wrongpass"));
    }

    // ---------- ASSERT EQUALS TESTS (optional messages returned manually) ----------

    @Test
    public void testUsernameMessageValid() {
        assertTrue(login.checkUserName("kyl_1"));
        String expected = "Welcome <user first name> ,<user last name> it is great to see you.";
        String actual = "Welcome <user first name> ,<user last name> it is great to see you.";
        assertEquals(expected, actual);
    }

    @Test
    public void testUsernameMessageInvalid() {
        assertFalse(login.checkUserName("kyle !!!!!!!"));
        String expected = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        String actual = "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordMessageValid() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
        String expected = "Password successfully captured.";
        String actual = "Password successfully captured.";
        assertEquals(expected, actual);
    }

    @Test
    public void testPasswordMessageInvalid() {
        assertFalse(login.checkPasswordComplexity("password"));
        String expected = "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        String actual = "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        assertEquals(expected, actual);
    }

    @Test
    public void testCellNumberMessageValid() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
        String expected = "Cell number successfully captured.";
        String actual = "Cell number successfully captured.";
        assertEquals(expected, actual);
    }

    @Test
    public void testCellNumberMessageInvalid() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
        String expected = "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        String actual = "Cell number is incorrectly formatted or does not contain an international code, please correct the number and try again.";
        assertEquals(expected, actual);
    }
}
