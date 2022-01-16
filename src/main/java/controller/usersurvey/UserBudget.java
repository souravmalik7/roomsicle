package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.ownersurvey.OwnerSurveyConstants.ONE;

public class UserBudget implements IUserSurvey {

    static final Logger logger = LogManager.getLogger(UserBudget.class);

    UserSurveyModel userSurveyModel;
    boolean hasValidValue = false;
    int userBudget;

    public UserBudget() {
    }

    public UserBudget(UserSurveyModel userSurveyModel, int userBudget) {
        this.userSurveyModel = userSurveyModel;
        this.userBudget = userBudget;
    }

    //get budget value from user
    @Override
    public void getValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.budget.message"));
            userBudget = roomsicleCLI.getNumberResponse();
            logger.info("userBudget: " + userBudget);
            while (hasValidValue == false) {
                if (validateValue(userSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.budget.message"));
                    userBudget = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.invalid.input.message"));
            getValue(userSurveyModel);
        }
    }

    //validate budget value input from user
    @Override
    public boolean validateValue(UserSurveyModel userSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateUserBudget = false;
        try {
            logger.info("validating user input for user Budget: " + userBudget);
            if (userBudget >= ONE) {
                validateUserBudget = true;
                logger.info("valid user budget input");
                setValue(userSurveyModel);
            } else {
                logger.info("validation failed, invalid value entered");
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.user.budget.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.illegal.argument.exception.user.budget.message"));
        }
        return validateUserBudget;
    }

    //set budget value
    @Override
    public void setValue(UserSurveyModel userSurveyModel) {
        userSurveyModel.setUserBudget(userBudget);
        logger.info("user budget is set to: " + userBudget);
    }
}
