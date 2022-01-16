package controllertest.userloginandregistrationtest;

import Exception.EmailException;
import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.verifications.EmailVerfierController;
import controller.verifications.IEmailVerifierController;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmailVerfierControllerTest  {
    IEmailVerifierController iEmailVerifierController= ClassInitializer.initializer().getIEmailVerifierController();
    String userData;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test(expected=EmailException.class)
    public void emailDoesnotExistsTest() throws EmailException {
        userData=DatabaseQueryProperties.getDatabaseQueryPropertyValue("owner.login.email.password.query");
        iEmailVerifierController.emailDoesNotExists(CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.negative.test.email.message"),userData);
    }

    @Test()
    public void testEmailExistsNegative(){
        EmailVerfierController emailVerfierController=new EmailVerfierController();
        userData=DatabaseQueryProperties.getDatabaseQueryPropertyValue("owner.login.email.password.query");
        String actual=emailVerfierController.validateEmailExists(CommandLineInputProperties.getCommandLineInputPropertyValue("login.example.negative.test.email.message"),userData);
        String expected= CommandLineInputProperties.getCommandLineInputPropertyValue("login.fail.message");
        Assert.assertEquals(actual, expected);
    }

}