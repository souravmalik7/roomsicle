package controller.ownersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.propertypricepredictor.PropertyPriceCalculator;
import controller.usersurvey.UserRoommateGenderPreference;
import database.ownersurveydao.IOwnerSurveyDAO;
import models.ownersurveymodel.OwnerSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OwnerSurveyMain {

    static final Logger logger = LogManager.getLogger(UserRoommateGenderPreference.class);

    public void takeOwnerSurvey() {
        IOwnerSurvey bedroomCount = ClassInitializer.initializer().getBedroomCount();
        IOwnerSurvey dalhousieDistance = ClassInitializer.initializer().getDalhousieDistance();
        IOwnerSurvey downtownDistance = ClassInitializer.initializer().getDowntownDistance();
        IOwnerSurvey groceryStoreDistance = ClassInitializer.initializer().getGroceryStoreDistance();
        IOwnerSurvey propertyAddress = ClassInitializer.initializer().getPropertyAddress();
        IOwnerSurvey theaterDistance = ClassInitializer.initializer().getTheaterDistance();
        IOwnerSurvey utilities = ClassInitializer.initializer().getUtilities();
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        OwnerSurveyModel ownerSurveyModel = ClassInitializer.initializer().getOwnerSurveyModel();
        IOwnerSurveyDAO ownerSurveyDAO = ClassInitializer.initializer().getOwnerSurveyDAO();
        PropertyPriceCalculator propertyPriceCalculator = ClassInitializer.initializer().getPropertyPriceCalculator();

        //get logged-in user email id and set it in model
        ownerSurveyModel.setOwnerID(ControllerProperties.getControllerPropertyValue("user.logged.in.email.id"));
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.take.survey.message"));
        //get property address
        propertyAddress.getValue(ownerSurveyModel);
        //get bedroom count
        bedroomCount.getValue(ownerSurveyModel);
        //get utilities confirmation
        utilities.getValue(ownerSurveyModel);
        //get property distance from Dalhousie university
        dalhousieDistance.getValue(ownerSurveyModel);
        //get property distance from Grocery Store
        groceryStoreDistance.getValue(ownerSurveyModel);
        //get property distance from Downtown
        downtownDistance.getValue(ownerSurveyModel);
        //get property distance from Theater
        theaterDistance.getValue(ownerSurveyModel);
        //insert owner survey details
        ownerSurveyDAO.insertOwnerSurveyDetails(ownerSurveyModel);
        //update survey taken status for owner
        ownerSurveyDAO.updateOwnerSurveyTakenStatus();
        //calculate price of the property based on owner inputs
        propertyPriceCalculator.propertyPrice(ownerSurveyModel);
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.profile.creation.message"));
        try {
            ClassInitializer.initializer().getUserLoginController().userLoginController();
        } catch (Exception e) {
            logger.error("invalid credentials");
        }
    }
}
