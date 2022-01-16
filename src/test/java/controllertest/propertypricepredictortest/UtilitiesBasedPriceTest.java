package controllertest.propertypricepredictortest;

import controller.ControllerProperties;
import controller.propertypricepredictor.UtilitiesBasedPrice;
import models.ownersurveymodel.OwnerSurveyModel;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UtilitiesBasedPriceTest {

    UtilitiesBasedPrice utilitiesBasedPrice = new UtilitiesBasedPrice();
    OwnerSurveyModel ownerSurveyModel = new OwnerSurveyModel();

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @BeforeClass
    public static void init() {
        ControllerProperties.loadControllerPropertiesFile();
    }

    @Test
    public void calculatePriceWhenUtilitiesProvided() {
        int price;
        ownerSurveyModel.setUtilitiesProvided(true);
        price = utilitiesBasedPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(100, price);
    }

    @Test
    public void calculatePriceWhenUtilitiesNotProvided() {
        int price;
        ownerSurveyModel.setUtilitiesProvided(false);
        price = utilitiesBasedPrice.calculatePrice(ownerSurveyModel);
        Assert.assertEquals(0, price);
    }

}
