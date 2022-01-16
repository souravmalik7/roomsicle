package controller.getloggedinuser;

import java.util.ArrayList;

import controller.ClassInitializer;
import controller.ControllerProperties;
import database.fitroommatesdao.IUserPreferencesDAO;
import database.fitroommatesdao.UserPreferencesDAO;
import models.fitroommatemodels.IUserPreferenceModel;
import models.fitroommatemodels.UserPreferencesModel;

public class GetLoggedInUserController implements IGetLoggedInUserController{

	public UserPreferencesModel getLoggedInUser() {

		String loggedInUserId;
		IUserPreferenceModel loggedInUser;
		IUserPreferencesDAO userPreferences;
		ArrayList<UserPreferencesModel> listOfUserPreferences;

		loggedInUserId = ControllerProperties.getControllerPropertyValue("loggedInUser");
		loggedInUser = ClassInitializer.initializer().getUserPreferenceModel();
		userPreferences = ClassInitializer.initializer().getUserPreferenceDAO();
		listOfUserPreferences = userPreferences.getUserPreferences();

		for(UserPreferencesModel userPreferenceObject : listOfUserPreferences) {
			if((userPreferenceObject.getUserId()).equals(loggedInUserId)) {
				loggedInUser.setUserId(loggedInUserId);
				loggedInUser.setRoommateFoodHabits(userPreferenceObject.getRoommateFoodHabits());
				loggedInUser.setRoommateAlcohol(userPreferenceObject.getRoommateAlcohol());
				loggedInUser.setRoommateSmoke(userPreferenceObject.getRoommateSmoke());
				loggedInUser.setRoommateGender(userPreferenceObject.getRoommateGender());
			}
		}

		return (UserPreferencesModel) loggedInUser;

	}

}
