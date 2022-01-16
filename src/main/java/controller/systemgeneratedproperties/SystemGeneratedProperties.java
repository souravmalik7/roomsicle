package controller.systemgeneratedproperties;

import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import controller.ControllerConstant;
import controller.ControllerProperties;
import database.systemgeneratedpropertiesdao.ISystemGeneratedPropertiesDAO;
import database.ownersurveydao.OwnerSurveyDAO;
import models.systemgeneratedpropertiesmodel.SystemGeneratedPropertiesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import Exception.InvalidBidException;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemGeneratedProperties implements ISystemGeneratedProperties {

    static final Logger logger = LogManager.getLogger(OwnerSurveyDAO.class);

    HashMap<String, Integer> userDetails = new HashMap<>();
    ArrayList<SystemGeneratedPropertiesModel> systemGeneratedPropertiesModels = new ArrayList<>();

    @Override
    public void initializeSystemGeneratedProperties()  {
        getUserBudgetAndDistanceValues();
        getSystemGeneratedProperties();
        printSystemGeneratedProperties(systemGeneratedPropertiesModels);
        ClassInitializer.initializer().getNavigator().navigator();
    }

    //get user budget and distance values
    @Override
    public HashMap<String, Integer> getUserBudgetAndDistanceValues() {
        ISystemGeneratedPropertiesDAO systemGeneratedPropertiesDAO = ClassInitializer.initializer().getSystemGeneratedPropertiesDAO();
        logger.info("Getting user budget and distance from dalhousie preference");
        userDetails = systemGeneratedPropertiesDAO.getUserBudgetAndDistancePreference();
        return userDetails;
    }

    @Override
    public ArrayList<SystemGeneratedPropertiesModel> getSystemGeneratedProperties() {
        ISystemGeneratedPropertiesDAO systemGeneratedPropertiesDAO = ClassInitializer.initializer().getSystemGeneratedPropertiesDAO();
        logger.info("Getting system generated property details based on user preference");
        systemGeneratedPropertiesModels = systemGeneratedPropertiesDAO.getSystemGeneratedPropertyDetails(userDetails);
        return systemGeneratedPropertiesModels;
    }

    @Override
    public void printSystemGeneratedProperties(ArrayList<SystemGeneratedPropertiesModel> systemGeneratedPropertiesModels) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        int ownerCount = ControllerConstant.OWNER_COUNT;
        logger.info("Display properties matching user preferences");
        for (SystemGeneratedPropertiesModel propertiesModel : systemGeneratedPropertiesModels) {
            roomsicleCLI.printMessage(ControllerProperties.getControllerPropertyValue("system.generated.properties.display.owner.name")
                    .replace("ownerCount", String.valueOf(ownerCount))
                    .replace("firstName", propertiesModel.getFirstName())
                    .replace("lastName", String.valueOf(propertiesModel.getLastName())));
            roomsicleCLI.printMessage(ControllerProperties.getControllerPropertyValue("system.generated.properties.display.property.address")
                    .replace("propertyAddress", propertiesModel.getAddress()));
            roomsicleCLI.printMessage(ControllerProperties.getControllerPropertyValue("system.generated.properties.display.owner.email.id")
                    .replace("ownerEmailId", propertiesModel.getOwnerEmailId()));
            roomsicleCLI.printMessage(ControllerProperties.getControllerPropertyValue("system.generated.properties.display.owner.contact.number")
                    .replace("contactNumber", String.valueOf(propertiesModel.getContactNumber())));
            roomsicleCLI.printMessage(ControllerProperties.getControllerPropertyValue("system.generated.properties.display.property.rent")
                    .replace("propertyRent", String.valueOf(propertiesModel.getRent())));
            roomsicleCLI.printMessage(ControllerProperties.getControllerPropertyValue("system.generated.properties.display.dalhousie.distance")
                    .replace("dalhousieDistance", String.valueOf(propertiesModel.getDalhousieDistance())));
            roomsicleCLI.printMessage("");
            ownerCount++;
        }
    }
}
