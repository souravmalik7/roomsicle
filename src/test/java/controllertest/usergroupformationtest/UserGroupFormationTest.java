package controllertest.usergroupformationtest;


import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import models.usergroupformationmodel.IUserGroupFormationModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserGroupFormationTest {
    IUserGroupFormationModel usersGroupModel= ClassInitializer.initializer().getUserGroupFormationModel();
    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }
    @Test
    public void firstChoiceTest(){
        usersGroupModel.setFirstEmailId(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"),usersGroupModel.getFirstEmailId());
    }

    @Test
    public void secondChoiceTest(){
        usersGroupModel.setSecondEmailId(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"),usersGroupModel.getSecondEmailId());
    }

    @Test
    public void thirdChoiceTest(){
        usersGroupModel.setThirdEmailId(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("user.test.email.id"),usersGroupModel.getThirdEmailId());
    }
}