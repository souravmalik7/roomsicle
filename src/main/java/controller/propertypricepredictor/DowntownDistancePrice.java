package controller.propertypricepredictor;

import controller.ClassInitializer;

public class DowntownDistancePrice extends LocationBasedPrice {

    @Override
    IDistanceBasedPriceCalculator getLocation() {
        return ClassInitializer.initializer().getDistanceBasedDowntownPrice();
    }
}
