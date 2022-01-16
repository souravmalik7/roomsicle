package controller.propertypricepredictor;

import controller.ControllerProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DistanceBasedGroceryStorePrice implements IDistanceBasedPriceCalculator {

    static final Logger logger = LogManager.getLogger(DistanceBasedGroceryStorePrice.class);

    //get price of the property based on the property distance from Grocery Store
    @Override
    public int getPrice(int priority) {
        int distanceGroceryStorePrice = 0;
        logger.info("Getting price of the property based on the property distance from Grocery Store");
        if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one"))) {
            distanceGroceryStorePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one.grocery.store.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two"))) {
            distanceGroceryStorePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two.grocery.store.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three"))) {
            distanceGroceryStorePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three.grocery.store.price"));
        } else if (priority == Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.four"))) {
            distanceGroceryStorePrice = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.four.grocery.store.price"));
        } else {
            throw new IllegalArgumentException("invalid argument");
        }
        logger.info("Based on the Grocery Store distance from the property, property price is increased by: " + distanceGroceryStorePrice);
        return distanceGroceryStorePrice;
    }
}
