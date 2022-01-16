package controller.ownersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import commandline.RoomsicleCLI;
import controller.ClassInitializer;
import controller.usersurvey.UserAlcoholHabits;
import models.ownersurveymodel.OwnerSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyAddress implements IOwnerSurvey {

    static final Logger logger = LogManager.getLogger(UserAlcoholHabits.class);

    OwnerSurveyModel ownerSurveyModel;
    boolean hasValidValue = false;
    String propertyAddress;

    public PropertyAddress() {
    }

    public PropertyAddress(OwnerSurveyModel ownerSurveyModel, String propertyAddress) {
        this.ownerSurveyModel = ownerSurveyModel;
        this.propertyAddress = propertyAddress;
    }

    //get property address input from owner
    @Override
    public void getValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        try {
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.property.address.message"));
            propertyAddress = roomsicleCLI.getStringResponse();
            logger.info("property address input from owner: " + propertyAddress);
            while (hasValidValue == false) {
                if (validateValue(ownerSurveyModel)) {
                    hasValidValue = true;
                    break;
                } else {
                    getValue(ownerSurveyModel);
                }
            }
        } catch (Exception e) {
            logger.error("Exception occurred in property address class");
        }
    }

    //validate property address input from owner
    @Override
    public boolean validateValue(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        boolean propertyAddressValue = false;
        try {
            logger.info("validate property address input from owner: " + propertyAddress);
            if (propertyAddress == null || propertyAddress.isEmpty() || propertyAddress.isBlank()) {
                logger.error("validation failed, invalid value entered");
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.property.address.message"));
            } else {
                propertyAddressValue = true;
                setValue(ownerSurveyModel);
            }
        } catch (Exception e) {
            logger.error("Exception occurred while validating input");
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.illegal.argument.exception.property.address.message"));
        }
        return propertyAddressValue;
    }

    //set property address input
    @Override
    public void setValue(OwnerSurveyModel ownerSurveyModel) {
        ownerSurveyModel.setAddress(propertyAddress);
        logger.info("property address is set to: " + propertyAddress);
    }
}
