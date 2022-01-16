package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.usersurvey.UserSurveyConstants.*;

public class UserFoodHabits implements IUserSurvey {

    static final Logger logger = LogManager.getLogger(UserFoodHabits.class);

    UserSurveyModel userSurveyModel;
    int userFoodHabitsInput;
    boolean hasValidValue = false;
    String userFoodHabits;

    public UserFoodHabits() {
    }

    public UserFoodHabits(UserSurveyModel userSurveyModel, int userFoodHabitsInput) {
        this.userSurveyModel = userSurveyModel;
        this.userFoodHabitsInput = userFoodHabitsInput;
    }

    //get user food habits input
    @Override
    public void getValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.food.habits.message"));
            userFoodHabitsInput = roomsicleCLI.getNumberResponse();
            logger.info("User food habit input: " + userFoodHabitsInput);
            while (hasValidValue == false) {
                if (validateValue(userSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.food.habits.message"));
                    userFoodHabitsInput = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.invalid.input.message"));
            getValue(userSurveyModel);
        }
    }

    //validate user food habits input
    @Override
    public boolean validateValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateFoodHabitsResponse = false;
        try {
            logger.info("validating user food habits input");
            if (userFoodHabitsInput == ONE) {
                validateFoodHabitsResponse = true;
                userFoodHabits = VEG;
            } else if (userFoodHabitsInput == TWO) {
                validateFoodHabitsResponse = true;
                userFoodHabits = NON_VEG;
            } else if (userFoodHabitsInput == THREE) {
                validateFoodHabitsResponse = true;
                userFoodHabits = VEGAN;
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

    //set user food habit
    @Override
    public void setValue(UserSurveyModel userSurveyModel) {
        userSurveyModel.setUserFoodHabits(userFoodHabits);
        logger.info("set user food habits input to: " + userFoodHabits);
    }
}
