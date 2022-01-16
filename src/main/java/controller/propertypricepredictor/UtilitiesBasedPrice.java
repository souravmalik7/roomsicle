package controller.propertypricepredictor;

import controller.ControllerProperties;
import controller.usersurvey.UserAlcoholHabits;
import models.ownersurveymodel.OwnerSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UtilitiesBasedPrice implements ICalculateIndividualFeaturePrice {

    static final Logger logger = LogManager.getLogger(UserAlcoholHabits.class);

    @Override
    public int calculatePrice(OwnerSurveyModel ownerSurveyModel) {
        int utilitiesPrice = 0;
        boolean utilitiesProvided;

        utilitiesProvided = ownerSurveyModel.isUtilitiesProvided();
        logger.info("utilitiesProvided: " + utilitiesProvided);
        if (utilitiesProvided == true) {
            utilitiesPrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.utilities.price"));
        }
        logger.info("utilitiesPrice based on utilities provided: " + utilitiesPrice);
        return utilitiesPrice;
    }
}
