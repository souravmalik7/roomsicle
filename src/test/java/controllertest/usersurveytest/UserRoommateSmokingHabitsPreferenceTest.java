package controllertest.usersurveytest;

import commandline.CommandLineInputProperties;
import controller.usersurvey.UserRoommateSmokingHabitsPreference;
import models.usersurveymodel.UserSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserRoommateSmokingHabitsPreferenceTest {

    UserSurveyModel userSurveyModel = new UserSurveyModel();
    UserRoommateSmokingHabitsPreference userRoommateSmokingHabitsPreferenceInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateRoommateSmokingHabitsYesInputTest() {
        userRoommateSmokingHabitsPreferenceInput = new UserRoommateSmokingHabitsPreference(userSurveyModel, 1);
        userRoommateSmokingHabitsPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("Yes", userSurveyModel.getRoommateSmokingHabits());
    }

    @Test
    public void validateRoommateSmokingHabitsNoInputTest() {
        userRoommateSmokingHabitsPreferenceInput = new UserRoommateSmokingHabitsPreference(userSurveyModel, 2);
        userRoommateSmokingHabitsPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("No", userSurveyModel.getRoommateSmokingHabits());
    }

    @Test
    public void validateRoommateSmokingHabitsDoesNotMatterInputTest() {
        userRoommateSmokingHabitsPreferenceInput = new UserRoommateSmokingHabitsPreference(userSurveyModel, 3);
        userRoommateSmokingHabitsPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("Does not matter", userSurveyModel.getRoommateSmokingHabits());
    }

    @Test
    public void validateRoommateSmokingHabitsInvalidInputTest() {
        userRoommateSmokingHabitsPreferenceInput = new UserRoommateSmokingHabitsPreference(userSurveyModel, 4);
        Assert.assertFalse(userRoommateSmokingHabitsPreferenceInput.validateValue(userSurveyModel));
    }
}
