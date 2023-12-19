import javax.swing.*;
import java.io.File;

public class Main {
    private static final JButton browseButton = new JButton("Browse Directory");
    private static File directory;
    private static final JFrame frame = new JFrame("Image Cleaner");

    public static void main(String[] arg) {
        SwingUtilities.invokeLater(() -> {
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            browseButton.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    directory = fileChooser.getSelectedFile();
                    CountingFrame countingFrame = new CountingFrame(directory);
                    frame.dispose();
                }
            });
            frame.getContentPane().add(browseButton);
            frame.setSize(300, 100);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
