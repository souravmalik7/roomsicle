package controllertest.userloginandregistrationtest;

import Exception.PasswordNotMatchException;
import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.verifications.IPasswordVerifierController;
import controller.verifications.PasswordVerifierController;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PasswordVerifierControllerTest {
    IPasswordVerifierController passwordVerifierController= ClassInitializer.initializer().getIPasswordVerifierController();
    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void passwordVerifierValidatorControllerTest()  {

        String actual=passwordVerifierController.passwordVerifierValidatorController(CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.test.email.actual.message"),CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.test.email.actual.message"));
        String expected=CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.test.email.expected.message");
        Assert.assertEquals(actual,expected);
    }
    @Test
    public void passwordVerifierValidatorControllerNegativeTest() {
        PasswordVerifierController passwordVerifierController=new PasswordVerifierController();
        String actual=passwordVerifierController.passwordVerifierValidatorController(CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.test.email.actual.message"),CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.negative.test.email.message"));
        String expected=CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.test.email.failed.expected.message");
        Assert.assertEquals(actual,expected);
    }

    @Test(expected= PasswordNotMatchException.class)
    public void passwordVerifierValidatorControllerPasswordExceptionTest() throws PasswordNotMatchException {
        PasswordVerifierController passwordVerifierController=new PasswordVerifierController();
        passwordVerifierController.passwordVerifierController(CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.test.email.actual.message"),CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.negative.test.email.message"));
    }
}