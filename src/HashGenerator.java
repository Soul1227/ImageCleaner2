import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;

public class HashGenerator {

    public HashGenerator() {
    }

    /**
     * Genera el hash MD5 de un archivo de imagen.
     *
     * @param file Archivo de imagen para el cual se generará el hash.
     * @return Hash MD5 del archivo de imagen.
     */
    public String generateMD5Hash(File file) {
        try {
            // Crea una instancia de MessageDigest con el algoritmo MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // Utiliza un DigestInputStream para leer el contenido del archivo y actualizar el hash
            try (DigestInputStream dis = new DigestInputStream(new FileInputStream(file), md)) {
                // Lee el archivo completamente (no necesitamos los datos, solo queremos actualizar el hash)
                while (dis.read() != -1) ;
                md = dis.getMessageDigest();
            }

            // Obtiene el hash como un array de bytes y lo convierte a una cadena hexadecimal
            byte[] hashBytes = md.digest();
            return bytesToHex(hashBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Convierte un array de bytes a una cadena hexadecimal.
     *
     * @param bytes Array de bytes.
     * @return Cadena hexadecimal.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
