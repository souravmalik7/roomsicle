package controllertest.usersurveytest;

import commandline.CommandLineInputProperties;
import controller.usersurvey.UserRoommateFoodHabitsPreference;
import models.usersurveymodel.UserSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserRoommateFoodHabitsPreferenceTest {

    UserSurveyModel userSurveyModel = new UserSurveyModel();
    UserRoommateFoodHabitsPreference userRoommateFoodHabitsPreferenceInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateRoommateVegFoodHabitsInputTest() {
        userRoommateFoodHabitsPreferenceInput = new UserRoommateFoodHabitsPreference(userSurveyModel, 1);
        userRoommateFoodHabitsPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("Veg", userSurveyModel.getRoommateFoodHabits());
    }

    @Test
    public void validateRoommateNonVegFoodHabitsInputTest() {
        userRoommateFoodHabitsPreferenceInput = new UserRoommateFoodHabitsPreference(userSurveyModel, 2);
        userRoommateFoodHabitsPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("Non-Veg", userSurveyModel.getRoommateFoodHabits());
    }

    @Test
    public void validateRoommateVeganFoodHabitsInputTest() {
        userRoommateFoodHabitsPreferenceInput = new UserRoommateFoodHabitsPreference(userSurveyModel, 3);
        userRoommateFoodHabitsPreferenceInput.validateValue(userSurveyModel);
        Assert.assertEquals("Vegan", userSurveyModel.getRoommateFoodHabits());
    }

    @Test
    public void validateInvalidRoommateFoodHabitsInputTest() {
        userRoommateFoodHabitsPreferenceInput = new UserRoommateFoodHabitsPreference(userSurveyModel, 4);
        Assert.assertFalse(userRoommateFoodHabitsPreferenceInput.validateValue(userSurveyModel));
    }

}
