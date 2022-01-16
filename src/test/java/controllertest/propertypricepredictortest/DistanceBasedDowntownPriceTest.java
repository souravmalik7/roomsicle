package controllertest.propertypricepredictortest;

import controller.ControllerProperties;
import controller.propertypricepredictor.DistanceBasedDowntownPrice;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DistanceBasedDowntownPriceTest {

    DistanceBasedDowntownPrice distanceBasedDowntownPrice = new DistanceBasedDowntownPrice();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init() {
        ControllerProperties.loadControllerPropertiesFile();
    }

    @Test
    public void getPriceDowntownPriorityOneTest() {
        int price;
        price = distanceBasedDowntownPrice.getPrice(1);
        Assert.assertEquals(70, price);
    }

    @Test
    public void getPriceDowntownPriorityTwoTest() {
        int price;
        price = distanceBasedDowntownPrice.getPrice(2);
        Assert.assertEquals(50, price);
    }

    @Test
    public void getPriceDowntownPriorityThreeTest() {
        int price;
        price = distanceBasedDowntownPrice.getPrice(3);
        Assert.assertEquals(30, price);
    }

    @Test
    public void getPriceDowntownPriorityFourTest() {
        int price;
        price = distanceBasedDowntownPrice.getPrice(4);
        Assert.assertEquals(20, price);
    }

    @Test
    public void getPriceDowntownInvalidPriorityTest() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        Assert.assertEquals(0, distanceBasedDowntownPrice.getPrice(5));
    }

    @Test
    public void getPriceDowntownNegativePriorityTest() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        Assert.assertEquals(0, distanceBasedDowntownPrice.getPrice(-1));
    }

}
