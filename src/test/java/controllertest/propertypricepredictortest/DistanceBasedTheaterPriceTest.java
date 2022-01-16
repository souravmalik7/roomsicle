package controllertest.propertypricepredictortest;

import controller.ControllerProperties;
import controller.propertypricepredictor.DistanceBasedTheaterPrice;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DistanceBasedTheaterPriceTest {

    DistanceBasedTheaterPrice distanceBasedTheaterPrice = new DistanceBasedTheaterPrice();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init() {
        ControllerProperties.loadControllerPropertiesFile();
    }

    @Test
    public void getPriceTheaterPriorityOneTest() {
        int price;
        price = distanceBasedTheaterPrice.getPrice(1);
        Assert.assertEquals(50, price);
    }

    @Test
    public void getPriceTheaterPriorityTwoTest() {
        int price;
        price = distanceBasedTheaterPrice.getPrice(2);
        Assert.assertEquals(30, price);
    }

    @Test
    public void getPriceTheaterPriorityThreeTest() {
        int price;
        price = distanceBasedTheaterPrice.getPrice(3);
        Assert.assertEquals(20, price);
    }

    @Test
    public void getPriceTheaterPriorityFourTest() {
        int price;
        price = distanceBasedTheaterPrice.getPrice(4);
        Assert.assertEquals(10, price);
    }

    @Test
    public void getPriceTheaterInvalidPriorityTest() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        Assert.assertEquals(0, distanceBasedTheaterPrice.getPrice(5));
    }

    @Test
    public void getPriceTheaterNegativePriorityTest() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        Assert.assertEquals(0, distanceBasedTheaterPrice.getPrice(-1));
    }
}
