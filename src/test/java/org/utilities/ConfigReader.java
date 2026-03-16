package org.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    // Constructor loads a specific property file
    public ConfigReader() {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("G:\\ja\\HrbridFramework\\src\\main\\resources\\Config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to get the value of a key
    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}