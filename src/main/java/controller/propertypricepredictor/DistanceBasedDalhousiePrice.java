package controller.propertypricepredictor;

import controller.ControllerProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DistanceBasedDalhousiePrice implements IDistanceBasedPriceCalculator {

    static final Logger logger = LogManager.getLogger(DistanceBasedDalhousiePrice.class);

    //Get price of the property based on the property distance from Dalhousie University
    @Override
    public int getPrice(int priority) {
        int distanceDalhousiePrice = 0;
        logger.info("Getting price of the property based on the property distance from Dalhousie University");
        if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one"))) {
            distanceDalhousiePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one.dalhousie.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two"))) {
            distanceDalhousiePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two.dalhousie.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three"))) {
            distanceDalhousiePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three.dalhousie.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.four"))) {
            distanceDalhousiePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.four.dalhousie.price"));
        } else {
            throw new IllegalArgumentException("invalid argument");
        }
        logger.info("Based on the Dalhousie distance from the property, property price is increased by: " + distanceDalhousiePrice);
        return distanceDalhousiePrice;
    }
}
