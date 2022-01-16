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

public class GroceryStoreDistance implements IOwnerSurvey {

    static final Logger logger = LogManager.getLogger(UserAlcoholHabits.class);

    OwnerSurveyModel ownerSurveyModel;
    boolean hasValidValue = false;
    int propertyDistanceFromGroceryStore;

    public GroceryStoreDistance() {
    }

    public GroceryStoreDistance(OwnerSurveyModel ownerSurveyModel, int propertyDistanceFromGroceryStore) {
        this.ownerSurveyModel = ownerSurveyModel;
        this.propertyDistanceFromGroceryStore = propertyDistanceFromGroceryStore;
    }

    //get grocery store distance input from owner
    @Override
    public void getValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.grocery.store.distance.message"));
            propertyDistanceFromGroceryStore = roomsicleCLI.getNumberResponse();
            logger.info("grocery store distance input from owner: " + propertyDistanceFromGroceryStore);
            while (hasValidValue == false) {
                if (validateValue(ownerSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.grocery.store.distance.message"));
                    propertyDistanceFromGroceryStore = roomsicleCLI.getNumberResponse();
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

    //validate grocery store distance input from owner
    @Override
    public boolean validateValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean distanceFromGroceryStore = false;
        try {
            logger.info("validate grocery store distance input from owner: " + propertyDistanceFromGroceryStore);
            if (propertyDistanceFromGroceryStore >= ONE) {
                distanceFromGroceryStore = true;
                setValue(ownerSurveyModel);
            } else {
                logger.error("validation failed, invalid value entered");
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.invalid.distance.message"));
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.invalid.distance.message"));
        }
        return distanceFromGroceryStore;
    }

    //set grocery store distance
    @Override
    public void setValue(OwnerSurveyModel ownerSurveyModel) {
        ownerSurveyModel.setGroceryStoreDistance(propertyDistanceFromGroceryStore);
        logger.info("grocery store distance is set to: " + propertyDistanceFromGroceryStore);
    }
}
