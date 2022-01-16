package controllertest.ownersurveytest;

import commandline.CommandLineInputProperties;
import controller.ownersurvey.BedroomCount;
import models.ownersurveymodel.OwnerSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BedroomCountTest {

    OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel();
    BedroomCount bedroomCountInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateValidBedroomInputTest() {
        bedroomCountInput = new BedroomCount(ownerSurveyModel, 5);
        bedroomCountInput.validateValue(ownerSurveyModel);
        Assert.assertEquals(5, ownerSurveyModel.getNumberOfBedrooms());
    }

    @Test
    public void validateNegativeBedroomInputTest() {
        bedroomCountInput = new BedroomCount(ownerSurveyModel, -1);
        bedroomCountInput.validateValue(ownerSurveyModel);
        Assert.assertEquals(0, ownerSurveyModel.getNumberOfBedrooms());
    }

    @Test
    public void validateNegativeBedroomInputReturnValueTest() {
        bedroomCountInput = new BedroomCount(ownerSurveyModel, -1);
        Assert.assertFalse(bedroomCountInput.validateValue(ownerSurveyModel));
    }

    @Test
    public void validateInValidBedroomInput() {
        bedroomCountInput = new BedroomCount(ownerSurveyModel, 10);
        Assert.assertFalse(bedroomCountInput.validateValue(ownerSurveyModel));
    }
}
