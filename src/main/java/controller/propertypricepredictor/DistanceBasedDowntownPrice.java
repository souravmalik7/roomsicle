package controller.propertypricepredictor;

import controller.ControllerProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DistanceBasedDowntownPrice implements IDistanceBasedPriceCalculator {

    static final Logger logger = LogManager.getLogger(DistanceBasedDowntownPrice.class);

    //get price of the property based on the property distance from Downtown
    @Override
    public int getPrice(int priority) {
        int distanceDowntownPrice = 0;
        logger.info("Getting price of the property based on the property distance from Downtown");
        if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one"))) {
            distanceDowntownPrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one.downtown.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two"))) {
            distanceDowntownPrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two.downtown.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three"))) {
            distanceDowntownPrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three.downtown.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.four"))) {
            distanceDowntownPrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.four.downtown.price"));
        } else {
            throw new IllegalArgumentException("invalid argument");
        }
        logger.info("Based on the Downtown distance from the property, property price is increased by: " + distanceDowntownPrice);
        return distanceDowntownPrice;
    }
}
