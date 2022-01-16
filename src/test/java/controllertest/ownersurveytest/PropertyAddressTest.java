package controllertest.ownersurveytest;

import commandline.CommandLineInputProperties;
import controller.ownersurvey.PropertyAddress;
import models.ownersurveymodel.OwnerSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class PropertyAddressTest {

    OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel();
    PropertyAddress propertyAddressInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateValidPropertyAddressInputTest() {
        propertyAddressInput = new PropertyAddress(ownerSurveyModel, "address");
        propertyAddressInput.validateValue(ownerSurveyModel);
        Assert.assertEquals("address", ownerSurveyModel.getAddress());
    }

    @Test
    public void validateNullPropertyAddressInputTest() {
        propertyAddressInput = new PropertyAddress(ownerSurveyModel, "");
        Assert.assertFalse(propertyAddressInput.validateValue(ownerSurveyModel));
    }

    @Test
    public void validateEmptyPropertyAddressReturnValueTest() {
        propertyAddressInput = new PropertyAddress(ownerSurveyModel, " ");
        Assert.assertFalse(propertyAddressInput.validateValue(ownerSurveyModel));
    }

    @Test
    public void validateNullPropertyAddressReturnValueTest() {
        propertyAddressInput = new PropertyAddress(ownerSurveyModel, null);
        Assert.assertFalse(propertyAddressInput.validateValue(ownerSurveyModel));
    }
}
