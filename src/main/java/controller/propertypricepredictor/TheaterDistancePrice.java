package controller.propertypricepredictor;

import controller.ClassInitializer;

public class TheaterDistancePrice extends LocationBasedPrice {

    @Override
    IDistanceBasedPriceCalculator getLocation() {
        return ClassInitializer.initializer().getDistanceBasedTheaterPrice();
    }
}
