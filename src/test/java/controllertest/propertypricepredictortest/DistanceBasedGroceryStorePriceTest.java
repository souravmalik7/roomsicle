package controllertest.propertypricepredictortest;

import controller.ControllerProperties;
import controller.propertypricepredictor.DistanceBasedGroceryStorePrice;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DistanceBasedGroceryStorePriceTest {

    DistanceBasedGroceryStorePrice distanceBasedGroceryStorePrice = new DistanceBasedGroceryStorePrice();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init() {
        ControllerProperties.loadControllerPropertiesFile();
    }

    @Test
    public void getPriceGroceryStorePriorityOneTest() {
        int price;
        price = distanceBasedGroceryStorePrice.getPrice(1);
        Assert.assertEquals(90, price);
    }

    @Test
    public void getPriceGroceryStorePriorityTwoTest() {
        int price;
        price = distanceBasedGroceryStorePrice.getPrice(2);
        Assert.assertEquals(65, price);
    }

    @Test
    public void getPriceGroceryStorePriorityThreeTest() {
        int price;
        price = distanceBasedGroceryStorePrice.getPrice(3);
        Assert.assertEquals(35, price);
    }

    @Test
    public void getPriceGroceryStorePriorityFourTest() {
        int price;
        price = distanceBasedGroceryStorePrice.getPrice(4);
        Assert.assertEquals(25, price);
    }

    @Test
    public void getPriceGroceryStoreInvalidPriorityTest() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        Assert.assertEquals(0, distanceBasedGroceryStorePrice.getPrice(5));
    }

    @Test
    public void getPriceGroceryStoreNegativePriorityTest() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        Assert.assertEquals(0, distanceBasedGroceryStorePrice.getPrice(-1));
    }
}
