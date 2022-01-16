package controllertest.usersurveytest;

import commandline.CommandLineInputProperties;
import controller.usersurvey.UserFoodHabits;
import models.usersurveymodel.UserSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserFoodHabitsTest {

    UserSurveyModel userSurveyModel = new UserSurveyModel();
    UserFoodHabits userFoodHabitsInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateVegFoodHabitsInputTest() {
        userFoodHabitsInput = new UserFoodHabits(userSurveyModel, 1);
        userFoodHabitsInput.validateValue(userSurveyModel);
        Assert.assertEquals("Veg", userSurveyModel.getUserFoodHabits());
    }

    @Test
    public void validateNonVegFoodHabitsInputTest() {
        userFoodHabitsInput = new UserFoodHabits(userSurveyModel, 2);
        userFoodHabitsInput.validateValue(userSurveyModel);
        Assert.assertEquals("Non-Veg", userSurveyModel.getUserFoodHabits());
    }

    @Test
    public void validateVeganFoodHabitsInputTest() {
        userFoodHabitsInput = new UserFoodHabits(userSurveyModel, 3);
        userFoodHabitsInput.validateValue(userSurveyModel);
        Assert.assertEquals("Vegan", userSurveyModel.getUserFoodHabits());
    }

    @Test
    public void validateInvalidUserFoodHabitsInputTest() {
        userFoodHabitsInput = new UserFoodHabits(userSurveyModel, 4);
        Assert.assertFalse(userFoodHabitsInput.validateValue(userSurveyModel));
    }
}
