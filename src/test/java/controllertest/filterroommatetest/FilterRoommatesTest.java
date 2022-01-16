package controllertest.filterroommatetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.filterroommates.*;
import database.ConfigProperties;
import database.DatabaseQueryProperties;
import org.junit.BeforeClass;
import org.junit.Test;

import controller.ControllerProperties;

public class FilterRoommatesTest {

    @BeforeClass
    public static void init() {
        CommandLineInputProperties.loadCommandLineInputPropertiesFile();
        ControllerProperties.loadControllerPropertiesFile();
        ConfigProperties.loadConfigPropertiesFile();
        DatabaseQueryProperties.loadDatabaseQueryPropertiesFile();
    }

    @Test
    public void testFilterRoommates() {
        IFilterRoommatesInput settedPreferences;
        HashMap<String, Integer> testMap = new HashMap<String, Integer>() {{
            put(ControllerProperties.getControllerPropertyValue("filterData1.key"), Integer.parseInt(ControllerProperties.getControllerPropertyValue("filterData1.value")));
            put(ControllerProperties.getControllerPropertyValue("filterData2.key"), Integer.parseInt(ControllerProperties.getControllerPropertyValue("filterData2.value")));
            put(ControllerProperties.getControllerPropertyValue("filterData3.key"), Integer.parseInt(ControllerProperties.getControllerPropertyValue("filterData3.value")));
            put(ControllerProperties.getControllerPropertyValue("filterData4.key"), Integer.parseInt(ControllerProperties.getControllerPropertyValue("filterData4.value")));
            put(ControllerProperties.getControllerPropertyValue("filterData5.key"), Integer.parseInt(ControllerProperties.getControllerPropertyValue("filterData5.value")));
            put(ControllerProperties.getControllerPropertyValue("filterData6.key"), Integer.parseInt(ControllerProperties.getControllerPropertyValue("filterData6.value")));
            put(ControllerProperties.getControllerPropertyValue("filterData8.key"), Integer.parseInt(ControllerProperties.getControllerPropertyValue("filterData8.value")));
            put(ControllerProperties.getControllerPropertyValue("filterData9.key"), Integer.parseInt(ControllerProperties.getControllerPropertyValue("filterData9.value")));
        }};
        settedPreferences = ClassInitializer.initializer().getFilterRoommatesInput();
        IFilterRoommates asker = mock(FilterRoommates.class);
        when(asker.filterRoommates(settedPreferences)).thenReturn(testMap);
        assertEquals(asker.filterRoommates(settedPreferences), testMap);
    }

}
