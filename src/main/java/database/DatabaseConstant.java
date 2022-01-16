package database;

import java.io.File;

public final class DatabaseConstant {

    public static final String PROJECT_HOME = System.getProperty("user.dir");
    public static final String DATABASE_QUERY_PROPERTIES_FILE_PATH = PROJECT_HOME + File.separator + "classes" + File.separator + "DatabaseQuery.properties";
    public static final String DATABASE_QUERY_PROPERTIES_FILE_PATH_UNDER_RESOURCES = PROJECT_HOME + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "DatabaseQuery.properties";

}
