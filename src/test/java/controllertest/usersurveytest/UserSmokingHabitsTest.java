package controllertest.usersurveytest;

import commandline.CommandLineInputProperties;
import controller.usersurvey.UserSmokingHabits;
import models.usersurveymodel.UserSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserSmokingHabitsTest {

    UserSurveyModel userSurveyModel = new UserSurveyModel();
    UserSmokingHabits userSmokingHabitsInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateSmokingHabitsYesInputTest() {
        userSmokingHabitsInput = new UserSmokingHabits(userSurveyModel, 1);
        userSmokingHabitsInput.validateValue(userSurveyModel);
        Assert.assertEquals("Yes", userSurveyModel.getUserSmokingHabits());
    }

    @Test
    public void validateSmokingHabitsNoInputTest() {
        userSmokingHabitsInput = new UserSmokingHabits(userSurveyModel, 2);
        userSmokingHabitsInput.validateValue(userSurveyModel);
        Assert.assertEquals("No", userSurveyModel.getUserSmokingHabits());
    }

    @Test
    public void validateSmokingHabitsInvalidInputTest() {
        userSmokingHabitsInput = new UserSmokingHabits(userSurveyModel, 3);
        Assert.assertFalse(userSmokingHabitsInput.validateValue(userSurveyModel));
    }
}
