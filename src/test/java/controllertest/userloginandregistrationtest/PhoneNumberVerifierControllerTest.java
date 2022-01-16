package controllertest.userloginandregistrationtest;

import Exception.PhoneNumberException;
import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.verifications.IPhoneNumberVerifierController;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.BeforeClass;
import org.junit.Test;

public class PhoneNumberVerifierControllerTest {
    IPhoneNumberVerifierController phoneNumberVerifierController= ClassInitializer.initializer().getIPhoneNumberVerifierController();
    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test(expected = PhoneNumberException.class)
    public void phoneNumberVerifierControllerExceptionTest() throws PhoneNumberException {
        phoneNumberVerifierController.phoneNumberVerifierController(0000);
    }
}