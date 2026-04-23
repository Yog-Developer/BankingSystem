package gui;
 
import javax.swing.SwingUtilities;
 
public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Theme.applyGlobalFont();
            new LoginScreen().setVisible(true);
        });
    }
}