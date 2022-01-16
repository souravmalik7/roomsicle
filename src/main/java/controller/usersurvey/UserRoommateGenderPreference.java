package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.usersurvey.UserSurveyConstants.*;

public class UserRoommateGenderPreference implements IUserSurvey {

    static final Logger logger = LogManager.getLogger(UserRoommateGenderPreference.class);

    UserSurveyModel userSurveyModel;
    int userRoommateGenderInput;
    boolean hasValidValue = false;
    String userRoommateGender;

    public UserRoommateGenderPreference() {
    }

    public UserRoommateGenderPreference(UserSurveyModel userSurveyModel, int userRoommateGenderInput) {
        this.userSurveyModel = userSurveyModel;
        this.userRoommateGenderInput = userRoommateGenderInput;
    }

    //get roommate gender preference input
    @Override
    public void getValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.roommate.gender.preferences.message"));
            userRoommateGenderInput = roomsicleCLI.getNumberResponse();
            logger.info("roommate food habits preference input: " + userRoommateGenderInput);
            while (hasValidValue == false) {
                if (validateValue(userSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.roommate.gender.preferences.message"));
                    userRoommateGenderInput = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.invalid.input.message"));
            getValue(userSurveyModel);
        }
    }

    //validate roommate gender preference input
    @Override
    public boolean validateValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateGenderResponse = false;
        try {
            logger.info("Validating roommate gender preference input");
            if (userRoommateGenderInput == ONE) {
                validateGenderResponse = true;
                userRoommateGender = MALE;
            } else if (userRoommateGenderInput == TWO) {
                validateGenderResponse = true;
                userRoommateGender = FEMALE;
            } else if (userRoommateGenderInput == THREE) {
                validateGenderResponse = true;
                userRoommateGender = DOES_NOT_MATTER;
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

    //set roommate gender preference input
    @Override
    public void setValue(UserSurveyModel userSurveyModel) {
        userSurveyModel.setRoommateGender(userRoommateGender);
        logger.info("roommate gender preference is set to: " + userRoommateGender);
    }
}
