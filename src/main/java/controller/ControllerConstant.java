package controller;

import java.io.File;

public final class ControllerConstant {

    public static final String PROJECT_HOME = System.getProperty("user.dir");
    public static final String CONTROLLER_PROPERTIES_FILE_PATH = PROJECT_HOME + File.separator + "classes" + File.separator + "Controller.properties";
    public static final int OWNER_COUNT = 1;
    public static final String CONTROLLER_PROPERTIES_FILE_PATH_UNDER_RESOURCES = PROJECT_HOME + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "Controller.properties";
}
