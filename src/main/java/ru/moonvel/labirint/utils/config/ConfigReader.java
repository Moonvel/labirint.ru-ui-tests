package ru.moonvel.labirint.utils.config;

import java.io.IOException;
import java.util.Properties;

public abstract class ConfigReader {
    private static final Properties properties = new Properties();


    public static void readProperties() {
        if (properties.isEmpty()) {
            try {
                properties.load(ClassLoader.getSystemResourceAsStream("config.properties"));
            } catch (IOException e) {
                throw new RuntimeException("Ошибка загрузки конфигурационного файла", e);
            }
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
