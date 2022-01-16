package controllertest.usersurveytest;

import commandline.CommandLineInputProperties;
import controller.usersurvey.UserUniversityDistancePreference;
import models.usersurveymodel.UserSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserUniversityDistancePreferenceTest {

    UserSurveyModel userSurveyModel = new UserSurveyModel();
    UserUniversityDistancePreference userUniversityDistancePreferenceInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateMinimumUniversityDistanceInputOneTest() {
        userUniversityDistancePreferenceInput = new UserUniversityDistancePreference(userSurveyModel, 1);
        userUniversityDistancePreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals(0, userSurveyModel.getUserDalDistanceMin());
    }

    @Test
    public void validateMaximumUniversityDistanceInputOneTest() {
        userUniversityDistancePreferenceInput = new UserUniversityDistancePreference(userSurveyModel, 1);
        userUniversityDistancePreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals(1, userSurveyModel.getUserDalDistanceMax());
    }

    @Test
    public void validateMinimumUniversityDistanceInputTwoTest() {
        userUniversityDistancePreferenceInput = new UserUniversityDistancePreference(userSurveyModel, 2);
        userUniversityDistancePreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals(1, userSurveyModel.getUserDalDistanceMin());
    }

    @Test
    public void validateMaximumUniversityDistanceInputTwoTest() {
        userUniversityDistancePreferenceInput = new UserUniversityDistancePreference(userSurveyModel, 2);
        userUniversityDistancePreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals(2, userSurveyModel.getUserDalDistanceMax());
    }

    @Test
    public void validateMinimumUniversityDistanceInputThreeTest() {
        userUniversityDistancePreferenceInput = new UserUniversityDistancePreference(userSurveyModel, 3);
        userUniversityDistancePreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals(2, userSurveyModel.getUserDalDistanceMin());
    }

    @Test
    public void validateMaximumUniversityDistanceInputThreeTest() {
        userUniversityDistancePreferenceInput = new UserUniversityDistancePreference(userSurveyModel, 3);
        userUniversityDistancePreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals(5, userSurveyModel.getUserDalDistanceMax());
    }

    @Test
    public void validateMinimumUniversityDistanceInputFourTest() {
        userUniversityDistancePreferenceInput = new UserUniversityDistancePreference(userSurveyModel, 4);
        userUniversityDistancePreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals(5, userSurveyModel.getUserDalDistanceMin());
    }

    @Test
    public void validateNegativeUniversityDistanceInputTest() {
        userUniversityDistancePreferenceInput = new UserUniversityDistancePreference(userSurveyModel, -1);
        Assert.assertFalse(userUniversityDistancePreferenceInput.validateValue(userSurveyModel));
    }

    @Test
    public void validateInvalidUniversityDistanceInputTest() {
        userUniversityDistancePreferenceInput = new UserUniversityDistancePreference(userSurveyModel, 0);
        Assert.assertFalse(userUniversityDistancePreferenceInput.validateValue(userSurveyModel));
    }
}
