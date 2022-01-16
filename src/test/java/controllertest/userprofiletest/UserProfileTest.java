package controllertest.userprofiletest;

import commandline.CommandLineInputProperties;
import controller.ControllerProperties;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import models.fitroommatemodels.UserDetailsModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserProfileTest {
    UserDetailsModel userDetailsModel=new UserDetailsModel();
    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void displayUserFirstNameTest(){
        userDetailsModel.setFirstName(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.first.name"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.first.name"),userDetailsModel.getFirstName());
    }

    @Test
    public void displayUserLastNameTest(){
        userDetailsModel.setLastName(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.last.name"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.last.name"),userDetailsModel.getLastName());
    }

    @Test
    public void displayUserContactNumberTest(){
        userDetailsModel.setContactNumber(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.phone.number"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.phone.number"),userDetailsModel.getContactNumber());
    }

    @Test
    public void displayUserEmailIdTest(){
        userDetailsModel.setEmailId(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"),userDetailsModel.getEmailId());
    }

}