package controller.propertypricepredictor;

import controller.ClassInitializer;
import controller.ControllerProperties;
import models.ownersurveymodel.OwnerSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DistanceBasedTotalPrice implements ICalculateIndividualFeaturePrice {

    static final Logger logger = LogManager.getLogger(DistanceBasedTotalPrice.class);

    //calculate price of the property based on distance of the property from different sources
    public int calculatePrice(OwnerSurveyModel ownerSurveyModel) {
        LocationBasedPriceFactory locationBasedPriceFactory = ClassInitializer.initializer().getLocationBasedPriceFactory();
        int dalDistance;
        int downtownDistance;
        int groceryDistance;
        int theaterDistance;
        int dalPriority;
        int downtownPriority;
        int groceryPriority;
        int theaterPriority;
        int dalPrice;
        int downtownPrice;
        int groceryPrice;
        int theaterPrice;
        int price;

        dalDistance = ownerSurveyModel.getDalhousieDistance();
        logger.info("dalDistance: " + dalDistance);
        dalPriority = getPriority(dalDistance);
        logger.info("dalPriority: " + dalPriority);
        dalPrice = locationBasedPriceFactory.getLocationBasedPrice(ControllerProperties.getControllerPropertyValue("property.price.predictor.location.dalhousie")).getLocation().getPrice(dalPriority);
        logger.info("dalPrice: " + dalPrice);

        groceryDistance = ownerSurveyModel.getGroceryStoreDistance();
        logger.info("groceryDistance: " + groceryDistance);
        groceryPriority = getPriority(groceryDistance);
        logger.info("groceryPriority: " + groceryPriority);
        groceryPrice = locationBasedPriceFactory.getLocationBasedPrice(ControllerProperties.getControllerPropertyValue("property.price.predictor.location.grocery.store")).getLocation().getPrice(groceryPriority);
        logger.info("groceryPrice: " + groceryPrice);

        downtownDistance = ownerSurveyModel.getDowntownDistance();
        logger.info("downtownDistance: " + downtownDistance);
        downtownPriority = getPriority(downtownDistance);
        logger.info("downtownPriority: " + downtownPriority);
        downtownPrice = locationBasedPriceFactory.getLocationBasedPrice(ControllerProperties.getControllerPropertyValue("property.price.predictor.location.downtown")).getLocation().getPrice(downtownPriority);
        logger.info("downtownPrice: " + downtownPrice);

        theaterDistance = ownerSurveyModel.getTheaterDistance();
        logger.info("theaterDistance: " + theaterDistance);
        theaterPriority = getPriority(theaterDistance);
        logger.info("theaterPriority: " + theaterPriority);
        theaterPrice = locationBasedPriceFactory.getLocationBasedPrice(ControllerProperties.getControllerPropertyValue("property.price.predictor.location.theater")).getLocation().getPrice(theaterPriority);
        logger.info("theaterPrice: " + theaterPrice);

        price = dalPrice + downtownPrice + groceryPrice + theaterPrice;
        logger.info("price: " + price);
        return price;
    }

    //get priority based on distance
    public int getPriority(int distance) {
        int priority = 0;

        if (distance > Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one.min.value")) && distance <= Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one.max.value"))) {
            priority = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one"));
        } else if (distance > Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.one.max.value")) && distance <= Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two.max.value"))) {
            priority = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two"));
        } else if (distance > Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.two.max.value")) && distance <= Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three.max.value"))) {
            priority = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three"));
        } else if (distance > Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.three.max.value")) && distance <= Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.four.max.value"))) {
            priority = Integer.parseInt(ControllerProperties.getControllerPropertyValue("property.price.predictor.distance.priority.four"));
        } else {
            throw new IllegalArgumentException("invalid argument");
        }
        logger.info("Priority: " + priority);
        return priority;
    }
}
