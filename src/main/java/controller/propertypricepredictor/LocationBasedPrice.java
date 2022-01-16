package controller.propertypricepredictor;

public abstract class LocationBasedPrice {

    abstract IDistanceBasedPriceCalculator getLocation();

}