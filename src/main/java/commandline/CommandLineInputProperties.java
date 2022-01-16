package commandline;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CommandLineInputProperties {

    private static Properties properties;

    public static void loadCommandLineInputPropertiesFile() {
        properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(CommandLineConstant.COMMAND_LINE_INPUT_PROPERTIES_FILE_PATH_UNDER_RESOURCES);
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            try {
                FileInputStream fileInputStream = new FileInputStream(CommandLineConstant.COMMAND_LINE_INPUT_PROPERTIES_FILE_PATH);
                properties.load(fileInputStream);
                fileInputStream.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCommandLineInputPropertyValue(String propertyKey) {
        String propertyValue = null;
        try {
            propertyValue = properties.getProperty(propertyKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propertyValue.trim();
    }
}
