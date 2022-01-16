package controllertest.userloginandregistrationtest;

import Exception.EmailException;
import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.verifications.IEmailFormatVerfier;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmailFormatVerifierControllerTest  {
    IEmailFormatVerfier emailFormatVerfier= ClassInitializer.initializer().getIEmailFormatVerfier();
    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void validEmailFormatVerifierPositiveTest(){
        Boolean actual;
        Boolean expected;
        actual=emailFormatVerfier.validEmailFormatVerifier(CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.test.email.message"));
        expected=true;
        Assert.assertEquals(actual, expected);
    }
    @Test
    public void validEmailFormatVerifierNegativeTest(){
        Boolean actual;
        Boolean expected;
        actual=emailFormatVerfier.validEmailFormatVerifier(CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.test.email.actual.message"));
        expected=false;
        Assert.assertEquals(actual, expected);
    }

    @Test(expected= EmailException.class)
    public void validEmailFormatVerifierExceptionTest() throws EmailException {
        emailFormatVerfier.emailFormatVerifier(CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.test.email.actual.message"));
    }
}