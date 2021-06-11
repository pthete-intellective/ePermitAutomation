package com.intellective.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;

public class Configuration {
    public static String config;
            public static String driverPath;
        public static String login;
        public static String password;

        private static final String Internal_XPATH_FILE = "internal-field-xpath.properties";
        private static final String External_XPATH_FILE = "external-field-xpath.properties";
        //private static final String External_XPATH_FILE = "external-field-xpath.properties";
//        private static final String Internal_TYPES_FILE = "field-types.properties";

        private Properties internalfieldXpath = ConfigReader.readPropertiesFile(Internal_XPATH_FILE);
        private Properties exteralfieldXpath = ConfigReader.readPropertiesFile(External_XPATH_FILE);
       // private Properties fieldTypes = Configuration.readPropertiesFile(TYPES_FILE);

        private Configuration() {
        }

        public static Configuration read() {
            Configuration configuration = new Configuration();
            configuration.internalfieldXpath = ConfigReader.readPropertiesFile(Internal_XPATH_FILE);
            configuration.exteralfieldXpath = ConfigReader.readPropertiesFile(External_XPATH_FILE);
            return configuration;
        }

        public String getXpath(String formName, String fieldName) {
            String key = formName + "." + fieldName;
            if(formName.contains("ExternalApp"))
                return checkValueForNull(key, exteralfieldXpath.getProperty(key));
            else
                return checkValueForNull(key, internalfieldXpath.getProperty(key));
        }

//        public String getType(String formName, String fieldName) {
//            String key = formName + "." + fieldName;
//            return fieldTypes.getProperty(key);
//        }

        private String checkValueForNull(String key, String value) {
            if (value == null) {
                throw new IllegalArgumentException("Key " + key + " value not found.");
            }
            return value;
        }

        public WebElement getWebElement(WebDriver driver, String formName, String name) {
            String xpath = getXpath(formName, name);
            return driver.findElement(By.xpath(xpath));
        }
        public List<WebElement> getWebElementes(WebDriver driver, String formName, String name){
            List<WebElement> elm;
            String xpath = getXpath(formName, name);
            try{
                elm = driver.findElements(By.xpath(xpath));
            }catch (NoSuchElementException e){
                elm=null;
            }
            return elm;
        }


}

