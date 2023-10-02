import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadFrequenciesFile {

    public static double[] loadFrequenciesFromFile(String filename) {
        double[] frequencies = new double[26];
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int index = 0;
            while ((line = br.readLine()) != null && index < 26) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    frequencies[index++] = Double.parseDouble(parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequencies;
    }
}
