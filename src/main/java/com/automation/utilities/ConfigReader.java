package com.automation.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try {
            FileInputStream input = new FileInputStream("src/main/resources/config.properties");
            properties.load(input);
            input.close();
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar el archivo config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
