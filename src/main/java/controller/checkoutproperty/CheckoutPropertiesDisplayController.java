package controller.checkoutproperty;

import commandline.CommandLineInputProperties;
import commandline.RoomsicleCLI;
import controller.ClassInitializer;
import database.PropertyDetailsDAO;
import models.ownersurveymodel.OwnerSurveyModel;

import java.util.*;

public class CheckoutPropertiesDisplayController implements ICheckoutPropertiesDisplayController {

    public static LinkedHashMap<Integer, Integer> sortHashMapByValues(
            HashMap<Integer, Integer> passedMap) {
        List<Integer> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<Integer, Integer> sortedMap =
                new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            int val = valueIt.next();
            Iterator<Integer> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Integer key = keyIt.next();
                int comp1 = passedMap.get(key);
                int comp2 = val;

                if (comp1 == comp2) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }

    	public void getCheckedOutProperties(ICheckoutProperties filterProperties) {

        ICheckoutPropertiesInput preferences = new CheckoutPropertiesInput();
        HashMap<Integer, Integer> propertiesMatchScoreMap = filterProperties.filterProperties(preferences);
        HashMap<Integer, Integer> sortedPropertiesList = sortHashMapByValues(propertiesMatchScoreMap);
        ArrayList<Integer> keys = new ArrayList<Integer>(sortedPropertiesList.keySet());

        PropertyDetailsDAO propertyDetails = new PropertyDetailsDAO();
        ArrayList<OwnerSurveyModel> listOfPropertyDetails = propertyDetails.getPropertyDetails();

        RoomsicleCLI roomsicleCLI = new RoomsicleCLI();
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.property.display.page.opening"));

        for (int i = keys.size() - 1; i > 0; i--) {
            int id = keys.get(i);
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.property.display.property.price") + listOfPropertyDetails.stream().filter(item -> item.getPropertyID() == id).findFirst().orElse(null).getPropertyPrice());
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.property.display.property.dalhousie.distance") + listOfPropertyDetails.stream().filter(item -> item.getPropertyID() == id).findFirst().orElse(null).getDalhousieDistance());
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.property.display.property.address") + listOfPropertyDetails.stream().filter(item -> item.getPropertyID() == id).findFirst().orElse(null).getAddress());
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.property.display.matched.preferences") + propertiesMatchScoreMap.get(listOfPropertyDetails.stream().filter(item -> item.getPropertyID() == id).findFirst().orElse(null).getPropertyID()));
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("separator"));
        }
            ClassInitializer.initializer().getNavigator().navigator();
    }


}
