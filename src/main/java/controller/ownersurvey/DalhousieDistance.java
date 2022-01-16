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

public class DalhousieDistance implements IOwnerSurvey {

    static final Logger logger = LogManager.getLogger(UserAlcoholHabits.class);

    OwnerSurveyModel ownerSurveyModel;
    boolean hasValidValue = false;
    int propertyDistanceFromDalhousie;

    public DalhousieDistance() {
    }

    public DalhousieDistance(OwnerSurveyModel ownerSurveyModel, int propertyDistanceFromDalhousie) {
        this.ownerSurveyModel = ownerSurveyModel;
        this.propertyDistanceFromDalhousie = propertyDistanceFromDalhousie;
    }

    //get dalhousie distance input from owner
    @Override
    public void getValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.dalhousie.university.distance.message"));
            propertyDistanceFromDalhousie = roomsicleCLI.getNumberResponse();
            logger.info("dalhousie distance input from owner: " + propertyDistanceFromDalhousie);
            while (hasValidValue == false) {
                if (validateValue(ownerSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.dalhousie.university.distance.message"));
                    propertyDistanceFromDalhousie = roomsicleCLI.getNumberResponse();
                }
            }
        } catch (InputMismatchException e) {
            logger.error("Input Mismatch exception occurred");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.invalid.input.message"));
            getValue(ownerSurveyModel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //validate dalhousie distance input from owner
    @Override
    public boolean validateValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean distanceFromDalhousie = false;
        try {
            logger.info("validating dalhousie distance input from owner: " + propertyDistanceFromDalhousie);
            if (propertyDistanceFromDalhousie >= ONE) {
                distanceFromDalhousie = true;
                setValue(ownerSurveyModel);
            } else {
                logger.error("validation failed, invalid value entered");
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.invalid.distance.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.invalid.distance.message"));
        }
        return distanceFromDalhousie;
    }

    //set dalhousie distance
    @Override
    public void setValue(OwnerSurveyModel ownerSurveyModel) {
        ownerSurveyModel.setDalhousieDistance(propertyDistanceFromDalhousie);
        logger.info("dalhousie distance is set to:" + propertyDistanceFromDalhousie);
    }
}
