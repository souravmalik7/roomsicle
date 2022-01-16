package controllertest.propertypricepredictortest;

import controller.ControllerProperties;
import controller.propertypricepredictor.PropertyBasePrice;
import models.ownersurveymodel.OwnerSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PropertyBasePriceTest {

    PropertyBasePrice propertyBasePrice = new PropertyBasePrice();
    OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init() {
        ControllerProperties.loadControllerPropertiesFile();
    }

    @Test
    public void calculateBasePricePositiveScenarioOne() {
        int price;
        ownerSurveyModel.setNumberOfBedrooms(1);
        price = propertyBasePrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(700, price);
    }

    @Test
    public void calculateBasePricePositiveScenarioTwo() {
        int price;
        ownerSurveyModel.setNumberOfBedrooms(2);
        price = propertyBasePrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(1000, price);
    }

    @Test
    public void calculateBasePricePositiveScenarioThree() {
        int price;
        ownerSurveyModel.setNumberOfBedrooms(3);
        price = propertyBasePrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(1300, price);
    }

    @Test
    public void calculateBasePricePositiveScenarioFour() {
        int price;
        ownerSurveyModel.setNumberOfBedrooms(4);
        price = propertyBasePrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(1600, price);
    }

    @Test
    public void calculateBasePricePositiveScenarioFive() {
        int price;
        ownerSurveyModel.setNumberOfBedrooms(5);
        price = propertyBasePrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(1900, price);
    }

    @Test
    public void calculateBasePricePositiveScenarioSix() {
        int price;
        ownerSurveyModel.setNumberOfBedrooms(6);
        price = propertyBasePrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(2200, price);
    }

    @Test
    public void calculateBasePriceNegativeScenarioInvalidValue() {
        ownerSurveyModel.setNumberOfBedrooms(7);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid number of bedrooms count");
        Assert.assertEquals(0, propertyBasePrice.calculatePrice(ownerSurveyModel));
    }

    @Test
    public void calculateBasePriceNegativeScenarioZero() {
        ownerSurveyModel.setNumberOfBedrooms(0);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid number of bedrooms count");
        Assert.assertEquals(0, propertyBasePrice.calculatePrice(ownerSurveyModel));
    }

    @Test
    public void calculateBasePriceNegativeScenarioNegativeValue() {
        ownerSurveyModel.setNumberOfBedrooms(-2);
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Invalid number of bedrooms count");
        Assert.assertEquals(0, propertyBasePrice.calculatePrice(ownerSurveyModel));
    }

}
