package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.usersurvey.UserSurveyConstants.*;

public class UserSmokingHabits implements IUserSurvey {

    static final Logger logger = LogManager.getLogger(UserRoommateGenderPreference.class);

    UserSurveyModel userSurveyModel;
    int userSmokingHabitsInput;
    boolean hasValidValue = false;
    String userSmokingHabits;

    public UserSmokingHabits() {
    }

    public UserSmokingHabits(UserSurveyModel userSurveyModel, int userSmokingHabitsInput) {
        this.userSurveyModel = userSurveyModel;
        this.userSmokingHabitsInput = userSmokingHabitsInput;
    }

    //get user smoking habit preference input
    @Override
    public void getValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.smoking.habits.message"));
            userSmokingHabitsInput = roomsicleCLI.getNumberResponse();
            logger.info("user smoking habit preference input: " + userSmokingHabitsInput);
            while (hasValidValue == false) {
                if (validateValue(userSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.smoking.habits.message"));
                    userSmokingHabitsInput = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.invalid.input.message"));
            getValue(userSurveyModel);
        }
    }

    //validate user smoking habit preference input
    @Override
    public boolean validateValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateSmokingHabitsResponse = false;
        try {
            logger.info("validating user smoking habit preference input: " + userSmokingHabitsInput);
            if (userSmokingHabitsInput == ONE) {
                validateSmokingHabitsResponse = true;
                userSmokingHabits = YES;
            } else if (userSmokingHabitsInput == TWO) {
                validateSmokingHabitsResponse = true;
                userSmokingHabits = NO;
            } else {
                logger.error("validation failed, invalid value entered");
            }
            if (validateSmokingHabitsResponse == true) {
                setValue(userSurveyModel);
            } else {
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.smoking.habits.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.smoking.habits.message"));
        }
        return validateSmokingHabitsResponse;
    }

    //get user smoking habit preference input
    @Override
    public void setValue(UserSurveyModel userSurveyModel) {
        userSurveyModel.setUserSmokingHabits(userSmokingHabits);
        logger.info("user smoking habit preference is set to: " + userSmokingHabits);
    }
}
