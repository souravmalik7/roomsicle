package controller.propertypricepredictor;

import controller.ControllerProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DistanceBasedTheaterPrice implements IDistanceBasedPriceCalculator {

    static final Logger logger = LogManager.getLogger(DistanceBasedTheaterPrice.class);

    //get price of the property based on the property distance from Theater
    @Override
    public int getPrice(int priority) {
        int distanceTheaterPrice = 0;
        logger.info("Getting price of the property based on the property distance from Theater");
        if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one"))) {
            distanceTheaterPrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one.theater.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two"))) {
            distanceTheaterPrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two.theater.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three"))) {
            distanceTheaterPrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three.theater.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.four"))) {
            distanceTheaterPrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.four.theater.price"));
        } else {
            throw new IllegalArgumentException("invalid argument");
        }
        logger.info("Based on the Theater distance from the property, property price is increased by: " + distanceTheaterPrice);
        return distanceTheaterPrice;
    }
}
