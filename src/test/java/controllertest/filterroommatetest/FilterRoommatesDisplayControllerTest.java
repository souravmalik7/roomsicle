package controllertest.filterroommatetest;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.filterroommates.IFilterRoommates;
import controller.filterroommates.IFilterRoommatesDisplayController;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FilterRoommatesDisplayControllerTest {

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void testFilterRoommatesDisplay() {
        IFilterRoommatesDisplayController filterRoommatesDisplayController;
        filterRoommatesDisplayController = ClassInitializer.initializer().getFilterRoommatesDisplayController();
        assertEquals(filterRoommatesDisplayController.getValidation(), false);
    }
}
