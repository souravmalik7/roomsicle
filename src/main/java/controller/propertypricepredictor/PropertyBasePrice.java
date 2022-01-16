package controller.propertypricepredictor;

import controller.ControllerProperties;
import controller.usersurvey.UserAlcoholHabits;
import models.ownersurveymodel.OwnerSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyBasePrice implements ICalculateIndividualFeaturePrice {

    static final Logger logger = LogManager.getLogger(UserAlcoholHabits.class);

    @Override
    public int calculatePrice(OwnerSurveyModel ownerSurveyModel) {
        int basePrice = 0;
        int numberOfBedrooms;
        numberOfBedrooms = ownerSurveyModel.getNumberOfBedrooms();
        logger.info("numberOfBedrooms: " + numberOfBedrooms);
        if (numberOfBedrooms == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.one"))) {
            basePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.one.base.price"));
        } else if (numberOfBedrooms == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.two"))) {
            basePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.two.base.price"));
        } else if (numberOfBedrooms == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.three"))) {
            basePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.three.base.price"));
        } else if (numberOfBedrooms == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.four"))) {
            basePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.four.base.price"));
        } else if (numberOfBedrooms == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.five"))) {
            basePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.five.base.price"));
        } else if (numberOfBedrooms == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.six"))) {
            basePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.property.number.of.bedroom.six.base.price"));
        } else {
            logger.error("Invalid number of bedrooms count");
            throw new IllegalArgumentException("Invalid number of bedrooms count");
        }
        logger.info("Base price based on number of bedrooms: " + basePrice);
        return basePrice;
    }
}
