package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.usersurvey.UserSurveyConstants.*;

public class UserGender implements IUserSurvey {

    static final Logger logger = LogManager.getLogger(UserGender.class);

    UserSurveyModel userSurveyModel;
    int userGenderInput;
    boolean hasValidValue = false;
    String userGender;

    public UserGender() {
    }

    public UserGender(UserSurveyModel userSurveyModel, int userGenderInput) {
        this.userSurveyModel = userSurveyModel;
        this.userGenderInput = userGenderInput;
    }

    //get user gender input
    @Override
    public void getValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.gender.message"));
            userGenderInput = roomsicleCLI.getNumberResponse();
            while (hasValidValue == false) {
                if (validateValue(userSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.gender.message"));
                    userGenderInput = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.invalid.input.message"));
            getValue(userSurveyModel);
        }
    }

    //validate user gender input
    @Override
    public boolean validateValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateGenderResponse = false;
        try {
            logger.info("Validating user gender input");
            if (userGenderInput == ONE) {
                validateGenderResponse = true;
                userGender = MALE;
            } else if (userGenderInput == TWO) {
                validateGenderResponse = true;
                userGender = FEMALE;
            } else if (userGenderInput == THREE) {
                validateGenderResponse = true;
                userGender = OTHER;
            } else {
                logger.error("validation failed, invalid value entered");
            }
            if (validateGenderResponse == true) {
                setValue(userSurveyModel);
            } else {
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.gender.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.gender.message"));
        }
        return validateGenderResponse;
    }

    //set user gender
    @Override
    public void setValue(UserSurveyModel userSurveyModel) {
        userSurveyModel.setUserGender(userGender);
        logger.info("User gender is set to: " + userGender);
    }
}
