package controllertest.usersurveytest;

import commandline.CommandLineInputProperties;
import controller.usersurvey.UserRoommateAlcoholHabitsPreference;
import database.ConfigProperties;
import models.usersurveymodel.UserSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserRoommateAlcoholHabitsPreferenceTest {

    UserSurveyModel userSurveyModel = new UserSurveyModel();
    UserRoommateAlcoholHabitsPreference userRoommateAlcoholHabitsPreferenceInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
    }

    @Test
    public void validateRoommateAlcoholHabitsYesInputTest() {
        userRoommateAlcoholHabitsPreferenceInput = new UserRoommateAlcoholHabitsPreference(userSurveyModel, 1);
        userRoommateAlcoholHabitsPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("Yes", userSurveyModel.getRoommateAlcoholHabits());
    }

    @Test
    public void validateRoommateAlcoholHabitsNoInputTest() {
        userRoommateAlcoholHabitsPreferenceInput = new UserRoommateAlcoholHabitsPreference(userSurveyModel, 2);
        userRoommateAlcoholHabitsPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("No", userSurveyModel.getRoommateAlcoholHabits());
    }

    @Test
    public void validateRoommateAlcoholHabitsDoesNotMatterInputTest() {
        userRoommateAlcoholHabitsPreferenceInput = new UserRoommateAlcoholHabitsPreference(userSurveyModel, 3);
        userRoommateAlcoholHabitsPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("Does not matter", userSurveyModel.getRoommateAlcoholHabits());
    }

    @Test
    public void validateAlcoholHabitsInvalidInputTest() {
        userRoommateAlcoholHabitsPreferenceInput = new UserRoommateAlcoholHabitsPreference(userSurveyModel, 4);
        Assert.assertFalse(userRoommateAlcoholHabitsPreferenceInput.validateValue(userSurveyModel));
    }

}
