package controllertest.userloginandregistrationtest;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import models.usermodel.IUsersModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserRegistrationControllerTest{
    IUsersModel usersModel= ClassInitializer.initializer().getIUsersModel();

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void setFirstNameTest() {
        usersModel.setFirstName(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.first.name"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.first.name"),usersModel.getFirstName());
    }

    @Test
    public void setLastNameTest() {
        usersModel.setLastName(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.last.name"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.last.name"),usersModel.getLastName());
    }

    @Test
    public void setContactTest() {
        usersModel.setContactNumber(1234567890);
        Assert.assertEquals(1234567890,usersModel.getContactNumber());
    }

    @Test
    public void setEmailTest() {
        usersModel.setEmailId(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"),usersModel.getEmailId());
    }

    @Test
    public void setPasswordTest() {
        usersModel.setPassword(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.password"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.password"),usersModel.getPassword());
    }
}