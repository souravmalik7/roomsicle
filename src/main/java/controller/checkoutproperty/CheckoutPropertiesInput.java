package controller.checkoutproperty;

import commandline.CommandLineInputProperties;
import commandline.RoomsicleCLI;
import controller.ControllerProperties;

import static controller.checkoutproperty.CheckoutPropertiesInputConstants.*;

public class CheckoutPropertiesInput implements ICheckoutPropertiesInput {

    int dalhousieDistancePreferenceSelect;
    String propertyPricePreference;
    String dalhousieDistancePreference;
    RoomsicleCLI roomsicleCLI = new RoomsicleCLI();
	
    public String[] setPreferences() {
        try {
        	roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.property.preference.details.message"));
        	roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.property.price.message"));
        	propertyPricePreference = roomsicleCLI.getStringResponse();
            
        	roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.dalhousie.distance.message"));
        	dalhousieDistancePreferenceSelect = roomsicleCLI.getNumberResponse();
        	if (dalhousieDistancePreferenceSelect == ONE){
        		dalhousieDistancePreference=ControllerProperties.getControllerPropertyValue("dalhousie.distance.one");
            }
            if (dalhousieDistancePreferenceSelect == TWO){
                dalhousieDistancePreference=ControllerProperties.getControllerPropertyValue("dalhousie.distance.two");
            }
            if (dalhousieDistancePreferenceSelect == THREE){
                dalhousieDistancePreference=ControllerProperties.getControllerPropertyValue("dalhousie.distance.three");
            }
            if (dalhousieDistancePreferenceSelect == FOUR){
                dalhousieDistancePreference=ControllerProperties.getControllerPropertyValue("dalhousie.distance.four");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        String preferencesList[] = {propertyPricePreference, dalhousieDistancePreference};
        return preferencesList;
    }

}