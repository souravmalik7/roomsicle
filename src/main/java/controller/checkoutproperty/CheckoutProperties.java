package controller.checkoutproperty;

import database.PropertyDetailsDAO;
import models.ownersurveymodel.OwnerSurveyModel;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckoutProperties implements ICheckoutProperties {

	public HashMap<Integer, Integer> filterProperties(ICheckoutPropertiesInput preferences) {
		int matchPreferenceCount;
		String[] preferencesList = preferences.setPreferences();

		PropertyDetailsDAO propertyPreferences = new PropertyDetailsDAO();
		ArrayList<OwnerSurveyModel> listOfPropertyPreferences = propertyPreferences.getPropertyDetails();

		HashMap<Integer, Integer> matchPreferenceCountMap = new HashMap<Integer, Integer>();

		for(OwnerSurveyModel propertyPreferenceObject : listOfPropertyPreferences) {
			matchPreferenceCount = 0;

			if(propertyPreferenceObject.getPropertyPrice() <= Integer.parseInt(preferencesList[0])) {
				matchPreferenceCount++;
			}
			if(Integer.parseInt(preferencesList[1]) == 1 && propertyPreferenceObject.getDalhousieDistance() <= 1) {
				matchPreferenceCount++;
			}
			if(Integer.parseInt(preferencesList[1]) == 2 && (propertyPreferenceObject.getDalhousieDistance() > 1 && propertyPreferenceObject.getDalhousieDistance() <=2)) {
				matchPreferenceCount++;
			}
			if(Integer.parseInt(preferencesList[1]) == 3 && (propertyPreferenceObject.getDalhousieDistance() > 2 && propertyPreferenceObject.getDalhousieDistance() <=5)) {
				matchPreferenceCount++;
			}
			if(Integer.parseInt(preferencesList[1]) == 4 && propertyPreferenceObject.getDalhousieDistance() > 5) {
				matchPreferenceCount++;
			}
			matchPreferenceCountMap.put(propertyPreferenceObject.getPropertyID(), matchPreferenceCount);
		}
		return matchPreferenceCountMap;
	}
}