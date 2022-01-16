package controller.propertypricepredictor;

import controller.ClassInitializer;

public class GroceryStoreDistancePrice extends LocationBasedPrice {

    @Override
    IDistanceBasedPriceCalculator getLocation() {
        return ClassInitializer.initializer().getDistanceBasedGroceryStorePrice();
    }
}
