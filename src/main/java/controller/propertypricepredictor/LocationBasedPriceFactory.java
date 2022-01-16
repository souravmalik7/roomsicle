package controller.propertypricepredictor;

import controller.ClassInitializer;
import controller.ControllerProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LocationBasedPriceFactory {

    static final Logger logger = LogManager.getLogger(LocationBasedPriceFactory.class);

    public LocationBasedPrice getLocationBasedPrice(String location) {
        logger.info("location: " + location);
        if (location.equalsIgnoreCase(ControllerProperties.getControllerPropertyValue("property.price.predictor.location.dalhousie"))) {
            return ClassInitializer.initializer().getDalhousieDistancePrice();
        } else if (location.equalsIgnoreCase(ControllerProperties.getControllerPropertyValue("property.price.predictor.location.grocery.store"))) {
            return ClassInitializer.initializer().getGroceryStoreDistancePrice();
        } else if (location.equalsIgnoreCase(ControllerProperties.getControllerPropertyValue("property.price.predictor.location.downtown"))) {
            return ClassInitializer.initializer().getDowntownDistancePrice();
        } else if (location.equalsIgnoreCase(ControllerProperties.getControllerPropertyValue("property.price.predictor.location.theater"))) {
            return ClassInitializer.initializer().getTheaterDistancePrice();
        } else {
            throw new IllegalArgumentException("invalid argument");
        }
    }
}
