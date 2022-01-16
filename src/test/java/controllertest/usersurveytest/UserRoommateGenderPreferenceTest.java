package controllertest.usersurveytest;

import commandline.CommandLineInputProperties;
import controller.usersurvey.UserRoommateGenderPreference;
import models.usersurveymodel.UserSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserRoommateGenderPreferenceTest {

    UserSurveyModel userSurveyModel = new UserSurveyModel();
    UserRoommateGenderPreference userRoommateGenderPreferenceInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateMaleUserGenderInputTest() {
        userRoommateGenderPreferenceInput = new UserRoommateGenderPreference(userSurveyModel, 1);
        userRoommateGenderPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("Male", userSurveyModel.getRoommateGender());
    }

    @Test
    public void validateFemaleUserGenderInputTest() {
        userRoommateGenderPreferenceInput = new UserRoommateGenderPreference(userSurveyModel, 2);
        userRoommateGenderPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("Female", userSurveyModel.getRoommateGender());
    }

    @Test
    public void validateOtherUserGenderInputTest() {
        userRoommateGenderPreferenceInput = new UserRoommateGenderPreference(userSurveyModel, 3);
        userRoommateGenderPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("Does not matter", userSurveyModel.getRoommateGender());
    }

    @Test
    public void validateInvalidUserGenderInputTest() {
        userRoommateGenderPreferenceInput = new UserRoommateGenderPreference(userSurveyModel, 4);
        Assert.assertEquals(false, userRoommateGenderPreferenceInput.validateValue(userSurveyModel));
    }

}
