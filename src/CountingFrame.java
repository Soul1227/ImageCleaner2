import javax.swing.*;
import java.awt.*;
import java.io.File;

public class CountingFrame {
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    private static final Filter filter = new Filter();
    File directory;

    public CountingFrame(File directory) {
        this.directory = directory;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.getContentPane().add(label, BorderLayout.NORTH);
        frame.setVisible(true);
        Counting();
        OpenProcessingFrame();
    }

    private void OpenProcessingFrame() {
        ProcessingFrame processingFrame = new ProcessingFrame(directory, filter.getCounter());
        close();
    }
    private void close(){
        this.frame.dispose();
    }

    private void Counting() {
        label.setText("counting");
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                filter.accept(file);
                label.setText("counting images: "+filter.getCounter());
            }
        } else {
            // Manejar el caso en el que files sea null (puede ocurrir si hay problemas de acceso al directorio)
            JOptionPane.showMessageDialog(null, "Error trying to access the directory.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}