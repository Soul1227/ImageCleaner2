import java.io.File;

public class Filter {
    private final String[] allowedExtensions = {".png", ".jpg", ".jpeg"};
    private int counter = 0;

    public Filter() {
    }

    public boolean accept(File file) {
        String fileName = file.getName().toLowerCase();
        for (String extension : allowedExtensions) {
            if (fileName.endsWith(extension)) {
                addcounter();
                return true; // Permitir archivos con extensiones especificadas
            }
        }
        return false;
    }

    public void addcounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
