package controllertest.ownersurveytest;

import commandline.CommandLineInputProperties;
import controller.ownersurvey.TheaterDistance;
import database.ConfigProperties;
import models.ownersurveymodel.OwnerSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TheaterDistanceTest {

    OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel();
    TheaterDistance theaterDistanceInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
    }

    @Test
    public void validateValidTheaterDistanceInputTest() {
        theaterDistanceInput = new TheaterDistance(ownerSurveyModel, 5);
        theaterDistanceInput.validateValue(ownerSurveyModel);
        Assert.assertEquals(5, ownerSurveyModel.getTheaterDistance());
    }

    @Test
    public void validateNegativeTheaterDistanceInputTest() {
        theaterDistanceInput = new TheaterDistance(ownerSurveyModel, -1);
        theaterDistanceInput.validateValue(ownerSurveyModel);
        Assert.assertEquals(0, ownerSurveyModel.getTheaterDistance());
    }

    @Test
    public void validateNegativeTheaterDistanceReturnValueTest() {
        theaterDistanceInput = new TheaterDistance(ownerSurveyModel, -1);
        Assert.assertFalse(theaterDistanceInput.validateValue(ownerSurveyModel));
    }

    @Test
    public void validateInvalidTheaterDistanceInputTest() {
        theaterDistanceInput = new TheaterDistance(ownerSurveyModel, 0);
        Assert.assertFalse(theaterDistanceInput.validateValue(ownerSurveyModel));
    }
}
