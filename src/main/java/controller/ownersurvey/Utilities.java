package controller.ownersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import commandline.RoomsicleCLI;
import controller.ClassInitializer;
import controller.usersurvey.UserAlcoholHabits;
import models.ownersurveymodel.OwnerSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

import static controller.ownersurvey.OwnerSurveyConstants.ONE;
import static controller.ownersurvey.OwnerSurveyConstants.TWO;

public class Utilities implements IOwnerSurvey {

    static final Logger logger = LogManager.getLogger(UserAlcoholHabits.class);

    OwnerSurveyModel ownerSurveyModel;
    boolean hasValidValue = false;
    boolean utilitiesIncluded;
    int utilitiesInput;

    public Utilities() {
    }

    public Utilities(OwnerSurveyModel ownerSurveyModel, int utilitiesInput) {
        this.ownerSurveyModel = ownerSurveyModel;
        this.utilitiesInput = utilitiesInput;
    }

    //get utilities input from owner
    @Override
    public void getValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.utilities.message"));
            utilitiesInput = roomsicleCLI.getNumberResponse();
            logger.info("utilities input from owner: " + utilitiesInput);
            while (hasValidValue == false) {
                if (validateValue(ownerSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.utilities.message"));
                    utilitiesInput = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.invalid.input.message"));
            getValue(ownerSurveyModel);
        }
    }

    //validate utilities input from owner
    @Override
    public boolean validateValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean utilitiesResponse = false;
        try {
            logger.info("validating utilities input from owner: " + utilitiesInput);
            if (utilitiesInput == ONE) {
                utilitiesResponse = true;
                utilitiesIncluded = true;
            } else if (utilitiesInput == TWO) {
                utilitiesResponse = true;
                utilitiesIncluded = false;
            } else {
                logger.error("validation failed, invalid value entered");
            }
            if (utilitiesResponse == true) {
                setValue(ownerSurveyModel);
            } else {
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.utilities.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.utilities.message"));
        }
        return utilitiesResponse;
    }

    //set utilities
    @Override
    public void setValue(OwnerSurveyModel ownerSurveyModel) {
        ownerSurveyModel.setUtilitiesProvided(utilitiesIncluded);
        logger.info("utilities is set to: " + utilitiesIncluded);
    }
}
