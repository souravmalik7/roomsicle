package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.usersurvey.UserSurveyConstants.*;

public class UserRoommateAlcoholHabitsPreference implements IUserSurvey {

    static final Logger logger = LogManager.getLogger(UserRoommateAlcoholHabitsPreference.class);

    UserSurveyModel userSurveyModel;
    int userRoommateAlcoholHabitsInput;
    boolean hasValidValue = false;
    String userRoommateAlcoholHabits;

    public UserRoommateAlcoholHabitsPreference() {
    }

    public UserRoommateAlcoholHabitsPreference(UserSurveyModel userSurveyModel, int userRoommateAlcoholHabitsInput) {
        this.userSurveyModel = userSurveyModel;
        this.userRoommateAlcoholHabitsInput = userRoommateAlcoholHabitsInput;
    }

    //get roommate alcohol preference input
    @Override
    public void getValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.roommate.alcohol.habits.message"));
            userRoommateAlcoholHabitsInput = roomsicleCLI.getNumberResponse();
            logger.info("roommate alcohol preference input: " + userRoommateAlcoholHabitsInput);
            while (hasValidValue == false) {
                if (validateValue(userSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.roommate.alcohol.habits.message"));
                    userRoommateAlcoholHabitsInput = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.invalid.input.message"));
            getValue(userSurveyModel);
        }
    }

    //validate roommate alcohol preference input
    @Override
    public boolean validateValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateAlcoholHabitsResponse = false;
        try {
            logger.info("Validating roommate alcohol preference input");
            if (userRoommateAlcoholHabitsInput == ONE) {
                validateAlcoholHabitsResponse = true;
                userRoommateAlcoholHabits = YES;
            } else if (userRoommateAlcoholHabitsInput == TWO) {
                validateAlcoholHabitsResponse = true;
                userRoommateAlcoholHabits = NO;
            } else if (userRoommateAlcoholHabitsInput == THREE) {
                validateAlcoholHabitsResponse = true;
                userRoommateAlcoholHabits = DOES_NOT_MATTER;
            } else {
                logger.error("validation failed, invalid value entered");
            }
            if (validateAlcoholHabitsResponse == true) {
                setValue(userSurveyModel);
            } else {
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.roommate.alcohol.habits.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.roommate.alcohol.habits.message"));
        }
        return validateAlcoholHabitsResponse;
    }

    //set roommate alcohol preference
    @Override
    public void setValue(UserSurveyModel userSurveyModel) {
        userSurveyModel.setRoommateAlcoholHabits(userRoommateAlcoholHabits);
        logger.info("roommate alcohol preference is set to: " + userRoommateAlcoholHabits);
    }
}

