package controllertest.systemgeneratedproperties;

import controller.ControllerProperties;
import controller.systemgeneratedproperties.SystemGeneratedProperties;
import database.ConfigProperties;
import mocks.SystemGeneratedPropertiesDAOMocks;
import models.systemgeneratedpropertiesmodel.SystemGeneratedPropertiesModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import java.util.ArrayList;

public class SystemGeneratedPropertiesTest {

    @BeforeClass
    public static void init() {
        ConfigProperties.loadConfigPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
    }

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    SystemGeneratedPropertiesDAOMocks systemGeneratedPropertiesDAOMocks = new SystemGeneratedPropertiesDAOMocks();
    SystemGeneratedProperties systemGeneratedProperties = new SystemGeneratedProperties();

    @Test
    public void printSystemGeneratedPropertiesPositiveScenarioTest() {
        ArrayList<SystemGeneratedPropertiesModel> systemGeneratedPropertiesModels;
        String expectedOutput = "";
        int ownerNumber;

        systemGeneratedPropertiesModels = systemGeneratedPropertiesDAOMocks.getSystemGeneratedPropertyDetails();
        systemGeneratedProperties.printSystemGeneratedProperties(systemGeneratedPropertiesModels);

        ownerNumber = 1;
        for (SystemGeneratedPropertiesModel systemGeneratedPropertiesModel : systemGeneratedPropertiesModels) {
            expectedOutput = expectedOutput + ownerNumber + ". Owner Name: " + systemGeneratedPropertiesModel.getFirstName() + " " + systemGeneratedPropertiesModel.getLastName() + System.getProperty("line.separator")
                    + "Property Address: " + systemGeneratedPropertiesModel.getAddress() + System.getProperty("line.separator") + "Owner email id: " + systemGeneratedPropertiesModel.getOwnerEmailId() + System.getProperty("line.separator")
                    + "Owner contact number: " + systemGeneratedPropertiesModel.getContactNumber() + System.getProperty("line.separator") + "Property Rent: " + systemGeneratedPropertiesModel.getRent() + System.getProperty("line.separator")
                    + "Distance from Dalhousie: " + systemGeneratedPropertiesModel.getDalhousieDistance() + " km" + System.getProperty("line.separator") + System.getProperty("line.separator");
            ownerNumber++;
        }
        Assert.assertEquals(expectedOutput, systemOutRule.getLog());
    }

    @Test
    public void printSystemGeneratedPropertiesNegativeScenarioTest() {
        ArrayList<SystemGeneratedPropertiesModel> systemGeneratedPropertiesModels;
        String expectedOutput = "";
        int ownerNumber;

        systemGeneratedPropertiesModels = systemGeneratedPropertiesDAOMocks.getSystemGeneratedPropertyDetails();
        systemGeneratedProperties.printSystemGeneratedProperties(systemGeneratedPropertiesModels);

        ownerNumber = 1;
        for (SystemGeneratedPropertiesModel systemGeneratedPropertiesModel : systemGeneratedPropertiesModels) {
            expectedOutput = expectedOutput + ownerNumber + ". Name: " + systemGeneratedPropertiesModel.getFirstName() + " " + systemGeneratedPropertiesModel.getLastName() + System.getProperty("line.separator")
                    + "Property Address: " + systemGeneratedPropertiesModel.getAddress() + System.getProperty("line.separator") + "Owner email id: " + systemGeneratedPropertiesModel.getOwnerEmailId() + System.getProperty("line.separator")
                    + "Owner contact number: " + systemGeneratedPropertiesModel.getContactNumber() + System.getProperty("line.separator") + "Property Rent: " + systemGeneratedPropertiesModel.getRent() + System.getProperty("line.separator")
                    + "Distance from Dalhousie: " + systemGeneratedPropertiesModel.getDalhousieDistance() + " km" + System.getProperty("line.separator") + System.getProperty("line.separator");
            ownerNumber++;
        }

        Assert.assertNotEquals(expectedOutput, systemOutRule.getLog());
    }

    @Test
    public void printSystemGeneratedPropertiesNegativeScenarioEmptyReturnValueTest() {
        ArrayList<SystemGeneratedPropertiesModel> systemGeneratedPropertiesModels;
        String expectedOutput;

        systemGeneratedPropertiesModels = systemGeneratedPropertiesDAOMocks.getSystemGeneratedPropertyDetails();
        systemGeneratedProperties.printSystemGeneratedProperties(systemGeneratedPropertiesModels);

        expectedOutput = "";
        Assert.assertNotEquals(expectedOutput, systemOutRule.getLog());
    }
}
