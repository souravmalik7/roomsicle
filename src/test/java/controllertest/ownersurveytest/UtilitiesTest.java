package controllertest.ownersurveytest;

import commandline.CommandLineInputProperties;
import controller.ownersurvey.Utilities;
import models.ownersurveymodel.OwnerSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UtilitiesTest {

    OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel();
    Utilities utilitiesInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateUtilitiesProvidedInputTest() {
        utilitiesInput = new Utilities(ownerSurveyModel, 1);
        utilitiesInput.validateValue(ownerSurveyModel);
        Assert.assertTrue(ownerSurveyModel.isUtilitiesProvided());
    }

    @Test
    public void validateUtilitiesNotProvidedInputTest() {
        utilitiesInput = new Utilities(ownerSurveyModel, 2);
        utilitiesInput.validateValue(ownerSurveyModel);
        Assert.assertFalse(ownerSurveyModel.isUtilitiesProvided());
    }

    @Test
    public void validateNegativeUtilitiesInputReturnValueTest() {
        utilitiesInput = new Utilities(ownerSurveyModel, -1);
        Assert.assertFalse(utilitiesInput.validateValue(ownerSurveyModel));
    }

    @Test
    public void validateInValidUtilitiesInputTest() {
        utilitiesInput = new Utilities(ownerSurveyModel, 10);
        Assert.assertFalse(utilitiesInput.validateValue(ownerSurveyModel));
    }

}
