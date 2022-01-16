package controllertest.propertypricepredictortest;

import controller.ControllerProperties;
import controller.propertypricepredictor.LocationBasedPrice;
import controller.propertypricepredictor.LocationBasedPriceFactory;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LocationBasedPriceFactoryTest {

    LocationBasedPriceFactory locationBasedPriceFactory = new LocationBasedPriceFactory();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init() {
        ControllerProperties.loadControllerPropertiesFile();
    }

    @Test
    public void getDalhousieLocationObjectTest() {
        LocationBasedPrice locationBasedPriceDalhousie;
        locationBasedPriceDalhousie = locationBasedPriceFactory.getLocationBasedPrice("Dalhousie");
        Assert.assertNotNull(locationBasedPriceDalhousie);
    }

    @Test
    public void getGroceryStoreLocationObjectTest() {
        LocationBasedPrice locationBasedPriceDalhousie;
        locationBasedPriceDalhousie = locationBasedPriceFactory.getLocationBasedPrice("Grocery Store");
        Assert.assertNotNull(locationBasedPriceDalhousie);
    }

    @Test
    public void getDowntownLocationObjectTest() {
        LocationBasedPrice locationBasedPriceDalhousie;
        locationBasedPriceDalhousie = locationBasedPriceFactory.getLocationBasedPrice("Downtown");
        Assert.assertNotNull(locationBasedPriceDalhousie);
    }

    @Test
    public void getTheaterLocationObjectTest() {
        LocationBasedPrice locationBasedPriceDalhousie;
        locationBasedPriceDalhousie = locationBasedPriceFactory.getLocationBasedPrice("Theater");
        Assert.assertNotNull(locationBasedPriceDalhousie);
    }

    @Test
    public void getLocationObjectNegativeScenarioStringTest() {
        LocationBasedPrice locationBasedPriceDalhousie;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        locationBasedPriceDalhousie = locationBasedPriceFactory.getLocationBasedPrice("Test");
        Assert.assertNull(locationBasedPriceDalhousie);
    }
}
