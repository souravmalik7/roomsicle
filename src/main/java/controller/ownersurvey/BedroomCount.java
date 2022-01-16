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
import static controller.ownersurvey.OwnerSurveyConstants.SIX;

public class BedroomCount implements IOwnerSurvey {

    static final Logger logger = LogManager.getLogger(UserAlcoholHabits.class);

    OwnerSurveyModel ownerSurveyModel;
    boolean hasValidValue = false;
    int numberOfBedrooms;

    public BedroomCount() {
    }

    public BedroomCount(OwnerSurveyModel ownerSurveyModel, int numberOfBedrooms) {
        this.ownerSurveyModel = ownerSurveyModel;
        this.numberOfBedrooms = numberOfBedrooms;
    }

    //get bedroom count input from owner
    @Override
    public void getValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.number.of.bedrooms.message"));
            numberOfBedrooms = roomsicleCLI.getNumberResponse();
            logger.info("bedroom count input from user: " + numberOfBedrooms);
            while (hasValidValue == false) {
                if (validateValue(ownerSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.number.of.bedrooms.message"));
                    numberOfBedrooms = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.invalid.input.message"));
            getValue(ownerSurveyModel);
        }
    }

    //validate bedroom count input from owner
    @Override
    public boolean validateValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean validateBedroomCount = false;
        try {
            logger.info("validating bedroom count input from user");
            if (numberOfBedrooms >= ONE && numberOfBedrooms <= SIX) {
                validateBedroomCount = true;
                setValue(ownerSurveyModel);
            } else {
                logger.error("validation failed, invalid value entered");
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.bedroom.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.bedroom.message"));
        }
        return validateBedroomCount;
    }

    //set bedroom count
    @Override
    public void setValue(OwnerSurveyModel ownerSurveyModel) {
        ownerSurveyModel.setNumberOfBedrooms(numberOfBedrooms);
        logger.info("bedroom count is set to: " + numberOfBedrooms);
    }
}
