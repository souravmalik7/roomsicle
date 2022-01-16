package database;

import controller.ControllerConstant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {

    private static Properties properties;

    public static void loadConfigPropertiesFile() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(ConfigConstant.CONFIG_PROPERTIES_FILE_PATH_UNDER_RESOURCES);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            try {
                FileInputStream fileInputStream = new FileInputStream(ConfigConstant.CONFIG_PROPERTIES_FILE_PATH);
                properties.load(fileInputStream);
                fileInputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigPropertyValue(String propertyKey) {
        String propertyValue = null;
        try {
            propertyValue = properties.getProperty(propertyKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertyValue.trim();
    }
}
