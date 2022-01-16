package controllertest.biddingpropertytest;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.propertybidding.IAvailableProperties;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AvailablePropertiesTest {

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void testAvailableProperties() {
        IAvailableProperties availableProperties = ClassInitializer.initializer().getAvailableProperties();
        availableProperties.displayProperties();
        assertEquals(availableProperties.getValidation(), true);
    }

    @Test
    public void testAvailablePropertiesTwo() {
        IAvailableProperties availableProperties = ClassInitializer.initializer().getAvailableProperties();
        assertEquals(availableProperties.getValidation(), false);
    }

}
