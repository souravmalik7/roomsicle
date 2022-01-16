package controller.propertypricepredictor;

import controller.ClassInitializer;

public class DalhousieDistancePrice extends LocationBasedPrice {

    @Override
    IDistanceBasedPriceCalculator getLocation() {
        return ClassInitializer.initializer().getDistanceBasedDalhousiePrice();
    }
}
