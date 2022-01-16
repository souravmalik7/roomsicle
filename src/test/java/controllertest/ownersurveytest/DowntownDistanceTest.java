package controllertest.ownersurveytest;

import commandline.CommandLineInputProperties;
import controller.ownersurvey.DowntownDistance;
import models.ownersurveymodel.OwnerSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DowntownDistanceTest {

    OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel();
    DowntownDistance downtownDistanceInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateValidDowntownDistanceInputTest() {
        downtownDistanceInput = new DowntownDistance(ownerSurveyModel, 5);
        downtownDistanceInput.validateValue(ownerSurveyModel);
        Assert.assertEquals(5, ownerSurveyModel.getDowntownDistance());
    }

    @Test
    public void validateNegativeDowntownDistanceInputTest() {
        downtownDistanceInput = new DowntownDistance(ownerSurveyModel, -1);
        downtownDistanceInput.validateValue(ownerSurveyModel);
        Assert.assertEquals(0, ownerSurveyModel.getDowntownDistance());
    }

    @Test
    public void validateNegativeDowntownDistanceReturnValueTest() {
        downtownDistanceInput = new DowntownDistance(ownerSurveyModel, -1);
        Assert.assertFalse(downtownDistanceInput.validateValue(ownerSurveyModel));
    }

    @Test
    public void validateInvalidDowntownDistanceInputTest() {
        downtownDistanceInput = new DowntownDistance(ownerSurveyModel, 0);
        Assert.assertFalse(downtownDistanceInput.validateValue(ownerSurveyModel));
    }
}
