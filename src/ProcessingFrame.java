import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProcessingFrame {
    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JButton button = new JButton("Start");
    JProgressBar progressBar = new JProgressBar();
    Filter filter = new Filter();
    HashGenerator hashGenerator = new HashGenerator();
    Map<String, File> imageHashesMap = new HashMap<>();
    File directory;
    int imagesAmount;

    public ProcessingFrame(File directory, int imagesAmount) {
        this.directory = directory;
        this.imagesAmount = imagesAmount;
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        // Crear un JPanel para contener el JLabel y la JProgressBar
        JPanel panel = new JPanel(new BorderLayout());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText((imagesAmount) + " Images");
        panel.add(label, BorderLayout.NORTH);

        progressBar.setStringPainted(true);
        panel.add(progressBar, BorderLayout.CENTER);

        // Otros ajustes y configuraciones...
        panel.add(button, BorderLayout.SOUTH);

        // Agregar el JPanel al contenedor principal del JFrame
        frame.getContentPane().add(panel);

        frame.setVisible(true);
        button.addActionListener(e -> {
            // Iniciar el SwingWorker cuando se hace clic en el botón
            SwingWorker<Void, Void> worker = new SwingWorker<>() {
                @Override
                protected Void doInBackground() {
                    FillingMap();
                    return null;
                }
            };
            worker.execute(); // Iniciar el SwingWorker
        });
    }

    private void FillingMap() {
        label.setText("Processing...");
        int totalFiles = imagesAmount;

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (filter.accept(file)) {
                imageHashesMap.put(hashGenerator.generateMD5Hash(file), file);

                // Calcular el progreso como un porcentaje
                int progress = (int) ((imageHashesMap.size() / (double) totalFiles) * 100);
                progressBar.setValue(progress);
            }
        }
    }
}

