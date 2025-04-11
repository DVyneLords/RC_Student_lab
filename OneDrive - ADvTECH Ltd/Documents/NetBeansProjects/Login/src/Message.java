
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

class Message {
    private String messageID;
    private String recipientCell;
    private String messageText;
    private String messageHash;
    private static List<Message> sentMessages = new ArrayList<>();
    private static List<Message> disregardedMessages = new ArrayList<>();
    private static List<Message> storedMessages = new ArrayList<>();

    public Message(String recipientCell, String messageText) {
        this.messageID = generateMessageID();
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageHash = createMessageHash(messageText);
        displayMessageDetails();
    }

    private String generateMessageID() {
        return String.valueOf(System.currentTimeMillis());
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        String regex = "^\\+27\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(recipientCell);
        return matcher.matches();
    }

    public String createMessageHash(String messageText) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(messageText.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String SentMessage() {
        String[] options = {"Send", "Store", "Disregard"};
        int action = JOptionPane.showOptionDialog(null, "Choose what to do with the message:", "Message Action",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (action) {
            case 0:
                sentMessages.add(this);
                return "Sent";
            case 1:
                storedMessages.add(this);
                return "Stored";
            case 2:
                disregardedMessages.add(this);
                return "Disregarded";
            default:
                return "No action taken";
        }
    }

    public static void sendMessage() {
        String recipientCell = JOptionPane.showInputDialog("Enter recipient cell number:");
        String messageText = JOptionPane.showInputDialog("Enter message:");
        Message message = new Message(recipientCell, messageText);
        String action = message.SentMessage();
        JOptionPane.showMessageDialog(null, action.equals("Sent") ? "Message sent successfully!" :
                (action.equals("Stored") ? "Message stored for later." : "Message discarded."));
    }

    public static void displayAllSentMessages() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sent messages.", "No Messages", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder messageList = new StringBuilder("All Sent Messages:\n");
            for (Message msg : sentMessages) {
                messageList.append(msg).append("\n");
            }
            JOptionPane.showMessageDialog(null, messageList.toString(), "Sent Messages", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void displayMessageDetails() {
        String messageDetails = "Message ID: " + messageID +
                "\nRecipient: " + recipientCell +
                "\nMessage: " + messageText +
                "\nHash: " + messageHash;
        JOptionPane.showMessageDialog(null, messageDetails, "Message Details", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String toString() {
        return "MessageID: " + messageID + ", Recipient: " + recipientCell + ", Text: " + messageText;
    }
}
