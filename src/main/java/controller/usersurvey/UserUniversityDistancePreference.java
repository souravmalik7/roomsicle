package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.usersurvey.UserSurveyConstants.*;

public class UserUniversityDistancePreference implements IUserSurvey {

    static final Logger logger = LogManager.getLogger(UserRoommateGenderPreference.class);

    UserSurveyModel userSurveyModel;
    boolean hasValidValue = false;
    int userUniversityDistanceInput;
    int userUniversityDistanceMin;
    int userUniversityDistanceMax;

    public UserUniversityDistancePreference() {
    }

    public UserUniversityDistancePreference(UserSurveyModel userSurveyModel, int userUniversityDistanceInput) {
        this.userSurveyModel = userSurveyModel;
        this.userUniversityDistanceInput = userUniversityDistanceInput;
    }

    //get user university distance preference input
    @Override
    public void getValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.proximity.dalhousie.university.message"));
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.university.distance.options.message"));
            userUniversityDistanceInput = roomsicleCLI.getNumberResponse();
            logger.info("user university distance preference input: " + userUniversityDistanceInput);
            while (hasValidValue == false) {
                if (validateValue(userSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.proximity.dalhousie.university.message"));
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.university.distance.options.message"));
                    userUniversityDistanceInput = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.invalid.input.message"));
            getValue(userSurveyModel);
        }
    }

    //validate user university distance preference input
    @Override
    public boolean validateValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateUniversityDistanceInput = false;
        try {
            logger.info("validating user university distance preference input: " + userUniversityDistanceInput);
            if (userUniversityDistanceInput == ONE) {
                validateUniversityDistanceInput = true;
                userUniversityDistanceMin = ZERO;
                userUniversityDistanceMax = ONE;
            } else if (userUniversityDistanceInput == TWO) {
                validateUniversityDistanceInput = true;
                userUniversityDistanceMin = ONE;
                userUniversityDistanceMax = TWO;
            } else if (userUniversityDistanceInput == THREE) {
                validateUniversityDistanceInput = true;
                userUniversityDistanceMin = TWO;
                userUniversityDistanceMax = FIVE;
            } else if (userUniversityDistanceInput == FOUR) {
                validateUniversityDistanceInput = true;
                userUniversityDistanceMin = FIVE;
                userUniversityDistanceMax = FIFTY;
            } else {
                logger.error("validation failed, invalid value entered");
            }
            if (validateUniversityDistanceInput == true) {
                setValue(userSurveyModel);
            } else {
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.university.distance.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.university.distance.message"));
        }
        return validateUniversityDistanceInput;
    }

    //set user university distance preference
    @Override
    public void setValue(UserSurveyModel userSurveyModel) {
        userSurveyModel.setUserDalDistanceMin(userUniversityDistanceMin);
        userSurveyModel.setUserDalDistanceMax(userUniversityDistanceMax);
        logger.info("Minimum university distance preference is set to: " + userUniversityDistanceMin);
        logger.info("Maximum university distance preference is set to: " + userUniversityDistanceMax);
    }
}
