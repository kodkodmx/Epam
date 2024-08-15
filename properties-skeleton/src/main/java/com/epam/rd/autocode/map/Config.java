package com.epam.rd.autocode.map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    private Properties config;

    public Config() throws IOException {
        reset();
    }

    public void reset() throws IOException {
        Properties defaults = new Properties();
        String mainConfigFile = "config.properties";

        // Load the main config properties first to determine default filenames
        Properties tempConfig = new Properties();
        loadProperties(mainConfigFile, tempConfig);

        // Load default properties in reverse order as required
        String defaultFiles = tempConfig.getProperty("default.filenames");
        if (defaultFiles != null) {
            String[] filenames = defaultFiles.split(",");
            for (int i = filenames.length - 1; i >= 0; i--) {
                loadProperties(filenames[i].trim() + ".properties", defaults);
            }
        }

        // Finally, load the main config file again to override any defaults
        config = new Properties(defaults);
        loadProperties(mainConfigFile, config);
    }

    public String get(String key) {
        return config.getProperty(key);
    }

    public void set(String key, String value) {
        config.setProperty(key, value);
    }

    public void save() throws IOException {
        try (FileOutputStream out = new FileOutputStream("config.properties")) {
            // Save only the main properties without defaults
            config.store(out, null);
        }
    }

    public void remove(String key) {
        config.remove(key);
    }

    private void loadProperties(String filename, Properties props) throws IOException {
        try (FileInputStream in = new FileInputStream(filename)) {
            props.load(in);
        }
    }
}
