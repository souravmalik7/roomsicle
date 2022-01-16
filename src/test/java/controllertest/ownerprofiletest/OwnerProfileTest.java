package controllertest.ownerprofiletest;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import models.ownerdetailsmodel.IOwnerDetailsModel;
import models.ownerpropertydetailsmodel.IOwnerPropertyDetailsModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class OwnerProfileTest {
    IOwnerDetailsModel ownerDetailsModel= ClassInitializer.initializer().getIOwnerDetailsModel();
    IOwnerPropertyDetailsModel ownerPropertyDetailsModel=ClassInitializer.initializer().getIOwnerPropertyDetailsModel();
    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void displayOwnerFirstNameTest(){
        ownerDetailsModel.setFirstName(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.first.name"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.first.name"),ownerDetailsModel.getFirstName());
    }

    @Test
    public void displayOwnerLastNameTest(){
        ownerDetailsModel.setLastName(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.last.name"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.last.name"),ownerDetailsModel.getLastName());
    }

    @Test
    public void displayOwnerContactNumberTest(){
        ownerDetailsModel.setContactNumber(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.contact.number"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.contact.number"),ownerDetailsModel.getContactNumber());
    }

    @Test
    public void displayOwnerEmailIdTest(){
        ownerDetailsModel.setEmailId(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.email.id"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.email.id"),ownerDetailsModel.getEmailId());
    }

    @Test
    public void displayOwnerPropertyAddressTest(){
        ownerPropertyDetailsModel.setAddress(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.property.address"));
        Assert.assertEquals(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.test.property.address"),ownerPropertyDetailsModel.getAddress());
    }

}