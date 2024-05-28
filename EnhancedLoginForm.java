import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Custom exception for invalid credentials
class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}

// Custom exception for missing inputs
class MissingInputsException extends Exception {
    public MissingInputsException(String message) {
        super(message);
    }
}

public class EnhancedLoginForm extends JFrame implements ActionListener {
    // Creating components
    private JLabel userLabel, passLabel, emailLabel, phoneLabel, messageLabel;
    private JTextField userText, emailText, phoneText;
    private JPasswordField passText;
    private JCheckBox showPassword;
    private JButton loginButton, clearButton;

    // Hardcoded credentials for validation
    private final String validUsername = "admin";
    private final String validPassword = "admin123";

    public EnhancedLoginForm() {
        // Setting up the frame
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Initializing components
        userLabel = new JLabel("Username:");
        passLabel = new JLabel("Password:");
        emailLabel = new JLabel("Email:");
        phoneLabel = new JLabel("Phone:");
        messageLabel = new JLabel();
        messageLabel.setForeground(Color.RED);

        userText = new JTextField();
        passText = new JPasswordField();
        emailText = new JTextField();
        phoneText = new JTextField();
        showPassword = new JCheckBox("Show Password");

        loginButton = new JButton("Login");
        clearButton = new JButton("Clear");

        // Adding tooltips
        userText.setToolTipText("Enter your username");
        passText.setToolTipText("Enter your password");
        emailText.setToolTipText("Enter your email");
        phoneText.setToolTipText("Enter your phone number");

        // Adding components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(userLabel, gbc);

        gbc.gridx = 1;
        add(userText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passLabel, gbc);

        gbc.gridx = 1;
        add(passText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(emailLabel, gbc);

        gbc.gridx = 1;
        add(emailText, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(phoneLabel, gbc);

        gbc.gridx = 1;
        add(phoneText, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(showPassword, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        add(messageLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        add(loginButton, gbc);

        gbc.gridx = 1;
        add(clearButton, gbc);

        // Adding action listeners
        loginButton.addActionListener(this);
        clearButton.addActionListener(this);
        showPassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPassword.isSelected()) {
                    passText.setEchoChar((char) 0);
                } else {
                    passText.setEchoChar('*');
                }
            }
        });

        // Frame settings
        setTitle("Enhanced Login Form");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == loginButton) {
            try {
                validateInputs(userText.getText(), new String(passText.getPassword()), emailText.getText(), phoneText.getText());
                validateCredentials(userText.getText(), new String(passText.getPassword()));
                JOptionPane.showMessageDialog(this, "You have successfully logged in!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
                messageLabel.setText("");
            } catch (MissingInputsException | InvalidCredentialsException e) {
                messageLabel.setText(e.getMessage());
            }
        } else if (ae.getSource() == clearButton) {
            userText.setText("");
            passText.setText("");
            emailText.setText("");
            phoneText.setText("");
            messageLabel.setText("");
        }
    }

    private void validateInputs(String username, String password, String email, String phone) throws MissingInputsException {
        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            throw new MissingInputsException("All fields must be filled out.");
        }
    }

    private void validateCredentials(String username, String password) throws InvalidCredentialsException {
        if (validUsername.equals(username) && validPassword.equals(password)) {
            // Credentials are valid
        } else {
            throw new InvalidCredentialsException("Invalid username or password.");
        }
    }

    public static void main(String[] args) {
        new EnhancedLoginForm();
    }
}
