package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.usersurvey.UserSurveyConstants.*;

public class UserRoommateFoodHabitsPreference implements IUserSurvey {

    static final Logger logger = LogManager.getLogger(UserRoommateFoodHabitsPreference.class);

    UserSurveyModel userSurveyModel;
    int userRoommateFoodHabitsInput;
    boolean hasValidValue = false;
    String userRoommateFoodHabits;

    public UserRoommateFoodHabitsPreference() {
    }

    public UserRoommateFoodHabitsPreference(UserSurveyModel userSurveyModel, int userRoommateFoodHabitsInput) {
        this.userSurveyModel = userSurveyModel;
        this.userRoommateFoodHabitsInput = userRoommateFoodHabitsInput;
    }

    //get roommate food habit preference input
    @Override
    public void getValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.roommate.food.habits.message"));
            userRoommateFoodHabitsInput = roomsicleCLI.getNumberResponse();
            logger.info("roommate food habit preference input: " + userRoommateFoodHabitsInput);
            while (hasValidValue == false) {
                if (validateValue(userSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.roommate.food.habits.message"));
                    userRoommateFoodHabitsInput = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.invalid.input.message"));
            getValue(userSurveyModel);
        }
    }

    //validate roommate food habit preference input
    @Override
    public boolean validateValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateFoodHabitsResponse = false;
        try {
            logger.info("Validating roommate food habit preference input");
            if (userRoommateFoodHabitsInput == ONE) {
                validateFoodHabitsResponse = true;
                userRoommateFoodHabits = VEG;
            } else if (userRoommateFoodHabitsInput == TWO) {
                validateFoodHabitsResponse = true;
                userRoommateFoodHabits = NON_VEG;
            } else if (userRoommateFoodHabitsInput == THREE) {
                validateFoodHabitsResponse = true;
                userRoommateFoodHabits = VEGAN;
            } else {
                logger.error("validation failed, invalid value entered");
            }
            if (validateFoodHabitsResponse == true) {
                setValue(userSurveyModel);
            } else {
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.food.habits.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.food.habits.message"));
        }
        return validateFoodHabitsResponse;
    }

    //set roommate food habit preference
    @Override
    public void setValue(UserSurveyModel userSurveyModel) {
        userSurveyModel.setRoommateFoodHabits(userRoommateFoodHabits);
        logger.info("roommate food habit preference is set to: " + userRoommateFoodHabits);
    }
}
