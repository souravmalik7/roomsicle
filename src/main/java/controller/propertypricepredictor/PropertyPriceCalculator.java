package controller.propertypricepredictor;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import controller.usersurvey.UserAlcoholHabits;
import database.propertypricepredictordao.IPropertyPricePredictorDAO;
import models.ownersurveymodel.OwnerSurveyModel;
import models.propertypricecalculatormodel.PropertyPriceCalculatorModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyPriceCalculator {

    static final Logger logger = LogManager.getLogger(UserAlcoholHabits.class);

    public void propertyPrice(OwnerSurveyModel ownerSurveyModel) {
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        PropertyPriceCalculatorModel propertyPriceCalculatorModel = ClassInitializer.initializer().getPropertyPriceCalculatorModel();
        IPropertyPricePredictorDAO propertyPricePredictorDAO = ClassInitializer.initializer().getPropertyPricePredictorDAO();
        int propertyPrice;
        int basePrice;
        int distanceBasedPrice;
        int utilitiesPrice;

        try {
            ICalculateIndividualFeaturePrice distanceIndividualPrice = ClassInitializer.initializer().getDistanceBasedTotalPrice();
            ICalculateIndividualFeaturePrice propertyBasePrice = ClassInitializer.initializer().getPropertyBasePrice();
            ICalculateIndividualFeaturePrice utilitiesBasedPrice = ClassInitializer.initializer().getUtilitiesBasedPrice();

            distanceBasedPrice = distanceIndividualPrice.calculatePrice(ownerSurveyModel);
            logger.info("distanceBasedPrice: " + distanceBasedPrice);
            basePrice = propertyBasePrice.calculatePrice(ownerSurveyModel);
            logger.info("basePrice: " + basePrice);
            utilitiesPrice = utilitiesBasedPrice.calculatePrice(ownerSurveyModel);
            logger.info("utilitiesPrice: " + utilitiesPrice);
            propertyPrice = distanceBasedPrice + basePrice + utilitiesPrice;
            logger.info("propertyPrice: " + propertyPrice);
            propertyPriceCalculatorModel.setPropertyPrice(propertyPrice);
            logger.info("inserting property price into owner survey table");
            propertyPricePredictorDAO.insertPropertyPrice(ownerSurveyModel, propertyPriceCalculatorModel);
            roomsicleCLI.printMessage(CommandLineInputProperties
                    .getCommandLineInputPropertyValue("property.price.predictor.display.property.price.message")
                    .replace("propertyPrice", String.valueOf(propertyPrice)) + "\n");
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
}
