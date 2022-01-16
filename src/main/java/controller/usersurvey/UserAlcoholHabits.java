package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.usersurvey.UserSurveyConstants.*;

public class UserAlcoholHabits implements IUserSurvey {

    static final Logger logger = LogManager.getLogger(UserAlcoholHabits.class);

    UserSurveyModel userSurveyModel;
    int userAlcoholHabitsInput;
    boolean hasValidValue = false;
    String userAlcoholHabits;

    public UserAlcoholHabits() {
    }

    public UserAlcoholHabits(UserSurveyModel userSurveyModel, int userAlcoholHabitsInput) {
        this.userSurveyModel = userSurveyModel;
        this.userAlcoholHabitsInput = userAlcoholHabitsInput;
    }

    //get alcohol habits input from user
    @Override
    public void getValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.alcohol.habits.message"));
            userAlcoholHabitsInput = roomsicleCLI.getNumberResponse();
            logger.info("userAlcoholHabitsInput:" + userAlcoholHabitsInput);
            while (hasValidValue == false) {
                if (validateValue(userSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.alcohol.habits.message"));
                    userAlcoholHabitsInput = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.invalid.input.message"));
            getValue(userSurveyModel);
        }
    }

    //validate alcohol habits input from user
    @Override
    public boolean validateValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateAlcoholHabitsResponse = false;
        try {
            logger.info("Validating alcohol habits user input: " + userAlcoholHabitsInput);
            if (userAlcoholHabitsInput == ONE) {
                validateAlcoholHabitsResponse = true;
                userAlcoholHabits = YES;
            } else if (userAlcoholHabitsInput == TWO) {
                validateAlcoholHabitsResponse = true;
                userAlcoholHabits = NO;
            } else {
                logger.error("validation failed, invalid value entered");
            }
            if (validateAlcoholHabitsResponse == true) {
                setValue(userSurveyModel);
            } else {
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.alcohol.habits.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.alcohol.habits.message"));
        }
        return validateAlcoholHabitsResponse;
    }

    //set alcohol habit of user
    @Override
    public void setValue(UserSurveyModel userSurveyModel) {
        userSurveyModel.setUserAlcoholHabits(userAlcoholHabits);
        logger.info("user alcohol habit is set");
    }
}
