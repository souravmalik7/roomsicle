package controllertest.getloggedinusertest;

import static org.junit.Assert.assertEquals;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.BeforeClass;
import org.junit.Test;
import controller.getloggedinuser.IGetLoggedInUserController;

public class GetLoggedInUserControllerTest {

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void testGetLoggedInUser() {
        IGetLoggedInUserController loggedInUser = ClassInitializer.initializer().getLoggedInUserController();
        assertEquals(loggedInUser.getLoggedInUser().getUserId(), ControllerProperties.getControllerPropertyValue("loggedInUser"));
    }

}
