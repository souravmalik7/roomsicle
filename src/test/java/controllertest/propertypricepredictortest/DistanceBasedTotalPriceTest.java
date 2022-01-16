package controllertest.propertypricepredictortest;

import controller.ControllerProperties;
import controller.propertypricepredictor.DistanceBasedTotalPrice;
import models.ownersurveymodel.OwnerSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DistanceBasedTotalPriceTest {

    DistanceBasedTotalPrice distanceBasedTotalPrice = new DistanceBasedTotalPrice();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init() {
        ControllerProperties.loadControllerPropertiesFile();
    }

    @Test
    public void calculatePricePositiveScenarioOneTest() {
        int distanceBasedPrice;
        OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel(1, 2, 3, 4);
        distanceBasedPrice = distanceBasedTotalPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(215, distanceBasedPrice);
    }

    @Test
    public void calculatePricePositiveScenarioTwoTest() {
        int distanceBasedPrice;
        OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel(3, 1, 2, 2);
        distanceBasedPrice = distanceBasedTotalPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(210, distanceBasedPrice);
    }

    @Test
    public void calculatePricePositiveScenarioThreeTest() {
        int distanceBasedPrice;
        OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel(4, 3, 2, 4);
        distanceBasedPrice = distanceBasedTotalPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(145, distanceBasedPrice);
    }

    @Test
    public void calculatePricePositiveScenarioFourTest() {
        int distanceBasedPrice;
        OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel(4, 4, 4, 4);
        distanceBasedPrice = distanceBasedTotalPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(125, distanceBasedPrice);
    }

    @Test
    public void calculatePricePositiveScenarioFiveTest() {
        int distanceBasedPrice;
        OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel(3, 1, 2, 3);
        distanceBasedPrice = distanceBasedTotalPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(200, distanceBasedPrice);
    }

    @Test
    public void calculatePriceDalhousieDistanceNegativeScenarioTest() {
        int distanceBasedPrice;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel(-1, 2, 2, 3);
        distanceBasedPrice = distanceBasedTotalPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(0, distanceBasedPrice);
    }

    @Test
    public void calculatePriceGroceryStoreDistanceNegativeScenarioTest() {
        int distanceBasedPrice;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel(1, -2, 2, 3);
        distanceBasedPrice = distanceBasedTotalPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(0, distanceBasedPrice);
    }

    @Test
    public void calculatePriceDowntownDistanceNegativeScenarioTest() {
        int distanceBasedPrice;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel(1, 2, -2, 3);
        distanceBasedPrice = distanceBasedTotalPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(0, distanceBasedPrice);
    }

    @Test
    public void calculatePriceTheaterDistanceNegativeScenarioTest() {
        int distanceBasedPrice;
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("invalid argument");
        OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel(1, 2, 2, -3);
        distanceBasedPrice = distanceBasedTotalPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(0, distanceBasedPrice);
    }
}
