package controllertest.propertypricepredictortest;

import controller.ControllerProperties;
import controller.propertypricepredictor.DistanceBasedDalhousiePrice;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DistanceBasedDalhousiePriceTest {

    DistanceBasedDalhousiePrice distanceBasedDalhousiePrice = new DistanceBasedDalhousiePrice();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init() {
        ControllerProperties.loadControllerPropertiesFile();
    }

    @Test
    public void getPriceDalhousiePriorityOneTest() {
        int price;
        price = distanceBasedDalhousiePrice.getPrice(1);
        Assert.assertEquals(100, price);
    }

    @Test
    public void getPriceDalhousiePriorityTwoTest() {
        int price;
        price = distanceBasedDalhousiePrice.getPrice(2);
        Assert.assertEquals(70, price);
    }

    @Test
    public void getPriceDalhousiePriorityThreeTest() {
        int price;
        price = distanceBasedDalhousiePrice.getPrice(3);
        Assert.assertEquals(40, price);
    }

    @Test
    public void getPriceDalhousiePriorityFourTest() {
        int price;
        price = distanceBasedDalhousiePrice.getPrice(4);
        Assert.assertEquals(30, price);
    }

    @Test
    public void getPriceDalhousieInvalidPriorityTest() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        Assert.assertEquals(0, distanceBasedDalhousiePrice.getPrice(5));
    }

    @Test
    public void getPriceDalhousieNegativePriorityTest() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        Assert.assertEquals(0, distanceBasedDalhousiePrice.getPrice(-1));
    }
}
