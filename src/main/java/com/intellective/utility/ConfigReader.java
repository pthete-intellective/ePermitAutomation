package com.intellective.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigReader {
    public static Properties p;

    /****
     * This method is used to load the config.properties file
     *
     */

        public ConfigReader() {
            // restrict
        }

        public static Properties readPropertiesFile(String filePath) {

            try {
                Properties p = new Properties();
                if (filePath != null && filePath.length() > 0) {
                    try (InputStream in = getStream(filePath)) {
                        p.load(in);
                        return p;
                    }
                } else {
                    System.out.println("Properties file path not set, please provide.");
                }
            } catch (IOException ex) {
                System.out.println("file " + filePath + " not found");
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
            return null;
        }

        private static InputStream getStream(String name) {
            return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
        }

}
