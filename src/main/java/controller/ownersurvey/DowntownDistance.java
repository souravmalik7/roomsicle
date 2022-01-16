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

public class DowntownDistance implements IOwnerSurvey {

    static final Logger logger = LogManager.getLogger(UserAlcoholHabits.class);

    OwnerSurveyModel ownerSurveyModel;
    boolean hasValidValue = false;
    int propertyDistanceFromDowntown;

    public DowntownDistance() {
    }

    public DowntownDistance(OwnerSurveyModel ownerSurveyModel, int propertyDistanceFromDowntown) {
        this.ownerSurveyModel = ownerSurveyModel;
        this.propertyDistanceFromDowntown = propertyDistanceFromDowntown;
    }

    //get downtown distance input from owner
    @Override
    public void getValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.downtown.distance.message"));
            propertyDistanceFromDowntown = roomsicleCLI.getNumberResponse();
            logger.info("downtown distance input from owner: " + propertyDistanceFromDowntown);
            while (hasValidValue == false) {
                if (validateValue(ownerSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.downtown.distance.message"));
                    propertyDistanceFromDowntown = roomsicleCLI.getNumberResponse();
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

    //validate downtown distance input from owner
    @Override
    public boolean validateValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean distanceFromDowntown = false;
        try {
            logger.info("validate downtown distance input from owner: " + propertyDistanceFromDowntown);
            if (propertyDistanceFromDowntown >= ONE) {
                distanceFromDowntown = true;
                setValue(ownerSurveyModel);
            } else {
                logger.error("validation failed, invalid value entered");
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.invalid.distance.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.invalid.distance.message"));
        }
        return distanceFromDowntown;
    }

    //set downtown distance
    @Override
    public void setValue(OwnerSurveyModel ownerSurveyModel) {
        ownerSurveyModel.setDowntownDistance(propertyDistanceFromDowntown);
        logger.info("downtown distance is set to: " + propertyDistanceFromDowntown);
    }
}
