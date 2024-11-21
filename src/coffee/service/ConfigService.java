package coffee.service;

import java.io.*;
import java.util.Properties;

public class ConfigService {
    private static final String CONFIG_FILE = "config.properties";
    private Properties properties;

    public ConfigService() {
        properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
        } catch (IOException ex) {
            System.out.println("Файл конфігурації не знайдено або не вдалося прочитати.");
        }
    }

    public double getMaxVolume() {
        return Double.parseDouble(properties.getProperty("truck.maxVolume", "100.0"));
    }

    public double getMaxBudget() {
        return Double.parseDouble(properties.getProperty("truck.maxBudget", "1000.0"));
    }

    public void saveProperties(double maxVolume, double maxBudget) {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            properties.setProperty("truck.maxVolume", String.valueOf(maxVolume));
            properties.setProperty("truck.maxBudget", String.valueOf(maxBudget));
            properties.store(output, null);
            System.out.println("Параметри збережено.");
        } catch (IOException ex) {
            System.out.println("Не вдалося зберегти файл конфігурації.");
        }
    }
}
