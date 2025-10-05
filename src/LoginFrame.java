import javax.swing.*;
import java.awt.*;
//import java.util.List;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        setTitle("Student Management System - LOGIN");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 182, 193));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(labelFont);
        JTextField usernameField = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(labelFont);
        JPasswordField passwordField = new JPasswordField(15);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(255, 105, 180));
        loginButton.setForeground(new Color(255, 105, 180));
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(userLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(passLabel, gbc);

        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        add(panel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            authenticate(username, password);
        });
    }

private void authenticate(String username, String password) {
    java.util.List<String> users = FileManager.readLines("login.txt");
    for (String user : users) {
        String[] parts = user.split(",");
        for (int i = 0; i < parts.length; i++) parts[i] = parts[i].trim();
        if (parts.length >= 3 && parts[0].equals(username) && parts[1].equals(password)) {
            String role = parts[2].toLowerCase();
            String id = parts.length >= 4 ? parts[3] : "";
            JOptionPane.showMessageDialog(this, "Welcome, " + username + "!");
            switch (role) {
                case "admin":
                    new AdminDashboard().setVisible(true);
                    break;
                case "student":
                    new StudentDashboard(id).setVisible(true);
                    break;
                case "instructor":
                    new InstructorDashboard(id).setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Unknown role: " + role);
                    return;
            }
            this.dispose();
            return;
        }
    }
    JOptionPane.showMessageDialog(this, "Invalid username or password!");
}

}
