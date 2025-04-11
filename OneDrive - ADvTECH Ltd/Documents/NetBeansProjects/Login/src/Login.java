import javax.swing.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {

    private String username;
    private String password;
    private String cellNumber;
    private String firstName;
    private String lastName;

    public static void main(String[] args) {
        Login login = new Login();
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!");
        login.registerUser();

        if (login.loginUser()) {
            login.mainMenu();
        } else {
            JOptionPane.showMessageDialog(null, "Username or password incorrect, please try again.");
        }
    }

    public boolean checkUserName(String username) {
        return username.length() >= 5 && username.contains("_");
    }

    public boolean checkPasswordComplexity(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[!@#$%^&*].*");
    }

    public boolean checkCellPhoneNumber(String cellNumber) {
        String regex = "^\\+\\d{11,15}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNumber);

        if (matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code.");
            return false;
        }
    }

    public void registerUser() {
        firstName = JOptionPane.showInputDialog("Enter your first name: ");
        lastName = JOptionPane.showInputDialog("Enter your last name: ");
        username = getValidUsername();
        password = getValidPassword();
        cellNumber = getValidCellNumber();
        JOptionPane.showMessageDialog(null, "Registration successful for " + firstName + " " + lastName);
    }

    private String getValidUsername() {
        String username;
        while (true) {
            username = JOptionPane.showInputDialog("Enter your username (5 characters with an underscore): ");
            if (checkUserName(username)) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "\"Username is not\n" +
"correctly formatted,\n" +
"please ensure that\n" +
"your username\n" +
"contains an\n" +
"underscore and is no\n" +
"more than five\n" +
"characters in length.\"");
            }
        }
        return username;
    }

    private String getValidPassword() {
        String password;
        while (true) {
            password = JOptionPane.showInputDialog("Enter your password (at least 8 characters, 1 uppercase, 1 digit, 1 special char): ");
            if (checkPasswordComplexity(password)) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "\"Password is not\n" +
"correctly formatted;\n" +
"please ensure that\n" +
"the password\n" +
"contains at least eight\n" +
"characters, a capital\n" +
"letter, a number, and\n" +
"a special character.\"");
            }
        }
        return password;
    }

    private String getValidCellNumber() {
        String cellNumber;
        while (true) {
            cellNumber = JOptionPane.showInputDialog("Enter your cell number (+countryCode phoneNumber): ");
            if (checkCellPhoneNumber(cellNumber)) {
                break;
            }
        }
        return cellNumber;
    }

    public boolean loginUser() {
    String enteredUsername = JOptionPane.showInputDialog("Enter your username: ");
    String enteredPassword = JOptionPane.showInputDialog("Enter your password: ");

    if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
        // Personalized message after successful login
        JOptionPane.showMessageDialog(null, "Welcome " + firstName + ", " + lastName + ", it is great to see you again.");
        return true;
    } else {
        return false;
    }
}


    private void mainMenu() {
        boolean exitApp = false;
        while (!exitApp) {
            String[] options = {"Send a Message", "Show All Sent Messages", "Quit"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Main Menu:\nChoose an option:",
                    "QuickChat Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            switch (choice) {
                case 0:
                    Message.sendMessage();
                    break;
                case 1:
                    Message.displayAllSentMessages();
                    break;
                case 2:
                    exitApp = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                    break;
            }
        }
        JOptionPane.showMessageDialog(null, "Exiting the application...");
    }
}
