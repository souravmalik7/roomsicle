package controller;

import commandline.CommandLineConstant;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ControllerProperties {

    private static Properties properties;

    public static void loadControllerPropertiesFile() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(ControllerConstant.CONTROLLER_PROPERTIES_FILE_PATH_UNDER_RESOURCES);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            try {
                FileInputStream fileInputStream = new FileInputStream(ControllerConstant.CONTROLLER_PROPERTIES_FILE_PATH);
                properties.load(fileInputStream);
                fileInputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getControllerPropertyValue(String propertyKey) {
        String propertyValue = null;
        try {
            propertyValue = properties.getProperty(propertyKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertyValue.trim();
    }

    public static void setControllerPropertyValue(String propertyKey, String propertyValue) {
        try {
            properties.setProperty(propertyKey, propertyValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
