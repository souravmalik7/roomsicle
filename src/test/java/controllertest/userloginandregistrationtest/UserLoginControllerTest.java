package controllertest.userloginandregistrationtest;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.userlogin.IPasswordValidity;
import controller.userlogin.IUserLoginController;
import controller.verifications.IUserIdValidation;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import Exception.PasswordNotMatchException;

import static controller.filterroommates.FilterRoommatesInputConstants.*;


public class UserLoginControllerTest {
    IUserLoginController userLoginController= ClassInitializer.initializer().getUserLoginController();
    IPasswordValidity passwordValidity=ClassInitializer.initializer().getIPasswordValidity();
    IUserIdValidation userIdValidation=ClassInitializer.initializer().getIUserIdValidation();

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();

    }

    @Test
    public void validatecheckCredsTest(){
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.email.id"),
                userLoginController.validatecheckCreds(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.email.id")));
    }

    @Test
    public void RoommateIdentityTest(){
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("welcomepage.roommate.identify.message"),
                userIdValidation.userIdValidation(ONE));
    }

    @Test
    public void OwnerIdentityTest(){
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("welcomepage.owner.identify.message"),
                userIdValidation.userIdValidation(TWO)); }

    @Test
    public void WrongIdentityTest(){
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.invalid.input.message")
                ,userIdValidation.userIdValidation(THREE));
    }

    @Test(expected = NullPointerException.class)
    public void NullEmailExceptionTest() throws PasswordNotMatchException{
        String checkCreds;
        checkCreds=DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.login.email.password.query");
        passwordValidity.getPasswordvalilidity(checkCreds,"","");
    }
    @Test(expected = PasswordNotMatchException.class)
    public void passwordNotMacthTest() throws PasswordNotMatchException {
        String checkCreds;
        checkCreds=DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.login.email.password.query");
        passwordValidity.getPasswordvalilidity(checkCreds,CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"),
                CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.first.name"));
    }
}