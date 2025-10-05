import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        FileManager.initializeFiles();
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}