package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.usersurvey.UserSurveyConstants.*;

public class UserRoommateSmokingHabitsPreference implements IUserSurvey {

    static final Logger logger = LogManager.getLogger(UserRoommateGenderPreference.class);

    UserSurveyModel userSurveyModel;
    int userRoommateSmokingHabitsInput;
    boolean hasValidValue = false;
    String userRoommateSmokingHabits;

    public UserRoommateSmokingHabitsPreference() {
    }

    public UserRoommateSmokingHabitsPreference(UserSurveyModel userSurveyModel, int userRoommateSmokingHabitsInput) {
        this.userSurveyModel = userSurveyModel;
        this.userRoommateSmokingHabitsInput = userRoommateSmokingHabitsInput;
    }

    //get roommate smoking habit preference input
    @Override
    public void getValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.roommate.smoking.habits.message"));
            userRoommateSmokingHabitsInput = roomsicleCLI.getNumberResponse();
            logger.info("roommate smoking habit preference input: " + userRoommateSmokingHabitsInput);
            while (hasValidValue == false) {
                if (validateValue(userSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.roommate.smoking.habits.message"));
                    userRoommateSmokingHabitsInput = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.invalid.input.message"));
            getValue(userSurveyModel);
        }
    }

    //validate roommate smoking habit preference input
    @Override
    public boolean validateValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateSmokingHabitsResponse = false;
        try {
            logger.info("validating roommate smoking habit preference input");
            if (userRoommateSmokingHabitsInput == ONE) {
                validateSmokingHabitsResponse = true;
                userRoommateSmokingHabits = YES;
            } else if (userRoommateSmokingHabitsInput == TWO) {
                validateSmokingHabitsResponse = true;
                userRoommateSmokingHabits = NO;
            } else if (userRoommateSmokingHabitsInput == THREE) {
                validateSmokingHabitsResponse = true;
                userRoommateSmokingHabits = DOES_NOT_MATTER;
            } else {
                logger.error("validation failed, invalid value entered");
            }
            if (validateSmokingHabitsResponse == true) {
                setValue(userSurveyModel);
            } else {
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.roommate.smoking.habits.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.roommate.smoking.habits.message"));
        }
        return validateSmokingHabitsResponse;
    }

    //set roommate smoking habit preference
    @Override
    public void setValue(UserSurveyModel userSurveyModel) {
        userSurveyModel.setRoommateSmokingHabits(userRoommateSmokingHabits);
        logger.info("roommate smoking habit preference is set to: " + userRoommateSmokingHabits);
    }
}

