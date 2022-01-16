package controllertest.usersurveytest;

import commandline.CommandLineInputProperties;
import controller.usersurvey.UserGender;
import models.usersurveymodel.UserSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserGenderTest {

    UserSurveyModel userSurveyModel = new UserSurveyModel();
    UserGender userGenderInput;

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
    }

    @Test
    public void validateMaleUserGenderInputTest() {
        userGenderInput = new UserGender(userSurveyModel, 1);
        userGenderInput.validateValue(userSurveyModel);
        Assert.assertEquals("Male", userSurveyModel.getUserGender());
    }

    @Test
    public void validateFemaleUserGenderInputTest() {
        userGenderInput = new UserGender(userSurveyModel, 2);
        userGenderInput.validateValue(userSurveyModel);
        Assert.assertEquals("Female", userSurveyModel.getUserGender());
    }

    @Test
    public void validateOtherUserGenderInputTest() {
        userGenderInput = new UserGender(userSurveyModel, 3);
        userGenderInput.validateValue(userSurveyModel);
        Assert.assertEquals("Other", userSurveyModel.getUserGender());
    }

    @Test
    public void validateInvalidUserGenderInputTest() {
        userGenderInput = new UserGender(userSurveyModel, 4);
        Assert.assertFalse(userGenderInput.validateValue(userSurveyModel));
    }
}
