package controller.bestfitroommates;

import java.util.ArrayList;
import java.util.HashMap;

import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.getloggedinuser.IGetLoggedInUserController;
import database.fitroommatesdao.IUserPreferencesDAO;
import database.fitroommatesdao.UserPreferencesDAO;
import models.fitroommatemodels.UserPreferencesModel;

public class BestFitRoommateController implements IBestFitRoommateController{

	public HashMap<String, Integer> findBestFit(IGetLoggedInUserController getLoggedInUserObject) {

		String loggedInUserId;
		IUserPreferencesDAO userPreferences;
		ArrayList<UserPreferencesModel> listOfUserPreferences;
		UserPreferencesModel loggedInUser;
		HashMap<String, Integer> matchScoresMap;

		loggedInUserId = ControllerProperties.getControllerPropertyValue("user.logged.in.email.id");
		//loggedInUserId = ControllerProperties.getControllerPropertyValue("loggedInUser");
		//Made Object of DAO class to get list of all users' preferences
		userPreferences = ClassInitializer.initializer().getUserPreferenceDAO();

		//userPreferences = new UserPreferencesDAO();
		listOfUserPreferences = userPreferences.getUserPreferences();

		//Get the logged-in user with getLoggedInUser() method of object made above
		loggedInUser = getLoggedInUserObject.getLoggedInUser();
		
		//Initiated counter variable for matching logged-in user's preferences with other user's preferences
		int matchScore;
		
		//Matched Preferences will be stored in the HashMap
		matchScoresMap = new HashMap<String, Integer>();

		//Comparing logged-in user preferences with other user's preferences 
		for(UserPreferencesModel userPreferenceObject : listOfUserPreferences) {
			matchScore = 0;
			if(userPreferenceObject.getUserId().equals(loggedInUserId)) {
				continue;
			}
			else {
				//Comparing food habits of users in the database
				if(loggedInUser.getRoommateFoodHabits().equals(userPreferenceObject.getRoommateFoodHabits())) {
					matchScore++;
				}
				//Comparing smoking habits of users in the database
				if(loggedInUser.getRoommateSmoke().equals(userPreferenceObject.getRoommateSmoke()) 
						|| loggedInUser.getRoommateSmoke().equals(ControllerProperties.getControllerPropertyValue("user.selected.preference.anypreference"))) {
					matchScore++;
				}
				//Comparing drinking habits of users in the database
				if(loggedInUser.getRoommateAlcohol().equals(userPreferenceObject.getRoommateAlcohol())
						|| loggedInUser.getRoommateAlcohol().equals(ControllerProperties.getControllerPropertyValue("user.selected.preference.anypreference"))) {
					matchScore++;
				}
				matchScoresMap.put(userPreferenceObject.getUserId(), matchScore);
			}
		}
		return matchScoresMap;
	}
}