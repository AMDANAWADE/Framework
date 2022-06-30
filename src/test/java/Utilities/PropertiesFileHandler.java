package Utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesFileHandler {

    private String propertiesFile;

    public PropertiesFileHandler(String filePath) {
        this.propertiesFile = filePath;
    }

    private PropertiesFileHandler() {
    }

    public String getProperty(String property) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(propertiesFile));
            return prop.getProperty(property);
        } catch (Exception e) {
            return null;
        }
    }

    public void setProperty(String property, String value) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(propertiesFile));
            prop.setProperty(property, value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
    }
}
