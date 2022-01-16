package commandline;

import java.io.File;

public final class CommandLineConstant {

    public static final String PROJECT_HOME = System.getProperty("user.dir");
    public static final String COMMAND_LINE_INPUT_PROPERTIES_FILE_PATH = PROJECT_HOME + File.separator + "classes" + File.separator + "CommandLineInput.properties";
    public static final String COMMAND_LINE_INPUT_PROPERTIES_FILE_PATH_UNDER_RESOURCES = PROJECT_HOME + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "CommandLineInput.properties";

}
