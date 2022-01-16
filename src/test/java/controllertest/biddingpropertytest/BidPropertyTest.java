package controllertest.biddingpropertytest;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.propertybidding.BidProperty;
import controller.propertybidding.IBidProperty;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import models.biddingmodels.IPropertyDetailsModel;
import models.biddingmodels.PropertyDetailsModel;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BidPropertyTest {

    PropertyDetailsModel propertyDetailsModel = new PropertyDetailsModel();

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void testBidProperty() {
        IBidProperty asker = mock(BidProperty.class);
        when(asker.getValidation()).thenReturn(true);
        assertEquals(asker.getValidation(), true);
    }

    @Test
    public void testBidProperty2() {
        IBidProperty asker = mock(BidProperty.class);
        when(asker.getValidation()).thenReturn(false);
        assertEquals(asker.getValidation(), false);
    }

    @Test
    public void checkPropertyTest() {
        propertyDetailsModel.setPropertyId("12");
        assertEquals("12", propertyDetailsModel.getPropertyId());
    }
}
