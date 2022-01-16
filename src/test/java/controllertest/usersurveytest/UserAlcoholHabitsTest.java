package controllertest.usersurveytest;

import commandline.CommandLineInputProperties;
import controller.usersurvey.UserAlcoholHabits;
import models.usersurveymodel.UserSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserAlcoholHabitsTest {

    UserSurveyModel userSurveyModel = new UserSurveyModel();
    UserAlcoholHabits userAlcoholHabitsInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateAlcoholHabitsYesInputTest() {
        userAlcoholHabitsInput = new UserAlcoholHabits(userSurveyModel, 1);
        userAlcoholHabitsInput.validateValue(userSurveyModel);
        Assert.assertEquals("Yes", userSurveyModel.getUserAlcoholHabits());
    }

    @Test
    public void validateAlcoholHabitsNoInputTest() {
        userAlcoholHabitsInput = new UserAlcoholHabits(userSurveyModel, 2);
        userAlcoholHabitsInput.validateValue(userSurveyModel);
        Assert.assertEquals("No", userSurveyModel.getUserAlcoholHabits());
    }

    @Test
    public void validateAlcoholHabitsInvalidInputTest() {
        userAlcoholHabitsInput = new UserAlcoholHabits(userSurveyModel, 3);
        Assert.assertFalse(userAlcoholHabitsInput.validateValue(userSurveyModel));
    }
}
