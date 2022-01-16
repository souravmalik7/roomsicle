package controller.filterroommates;

import java.util.ArrayList;
import java.util.HashMap;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import commandline.RoomsicleCLI;
import controller.ClassInitializer;
import controller.ControllerProperties;
import controller.usergroupformationcontroller.IUserGroupFormation;
import database.fitroommatesdao.IUserDetailsDAO;
import models.fitroommatemodels.UserDetailsModel;

public class FilterRoommatesDisplayController implements IFilterRoommatesDisplayController {

	boolean validation = false;
	//Method to display results of filtered roommates
	public void getFilteredFits(IFilterRoommates filteredPreferences) {

		String loggedInUserId;
		IFilterRoommatesInput preferences;
		HashMap<String, Integer> usersMatchScoreMap;
		IUserDetailsDAO userDetails;
		IRoomsicleCLI roomsicleCLI;
		ArrayList<UserDetailsModel> listOfUserDetails;
		IUserGroupFormation userGroupFormation;

		filteredPreferences = ClassInitializer.initializer().getFilterRoommates();
		
		loggedInUserId = ControllerProperties.getControllerPropertyValue("loggedInUser");;
		preferences = ClassInitializer.initializer().getFilterRoommatesInput();
		usersMatchScoreMap = filteredPreferences.filterRoommates(preferences);
		userDetails = ClassInitializer.initializer().getUserDetailsDAO();
		listOfUserDetails = userDetails.getUserDetails();
		userGroupFormation = ClassInitializer.initializer().getUserGroupFormation();

		//Object is created to print on the Command Line Interface(CLI)
		roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
		roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.roommate.display.page.opening"));

		for(UserDetailsModel usersDetails : listOfUserDetails) {
			if(usersDetails.getEmailId().equals(loggedInUserId)) {
				continue;
			}
			else {
				roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.roommate.display.user.firstname")+usersDetails.getFirstName());
				roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.roommate.display.user.lastname")+usersDetails.getLastName());
				roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.roommate.display.user.contactnumber")+usersDetails.getContactNumber());
				roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.roommate.display.user.emailid")+usersDetails.getEmailId());
				roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("filter.roommate.display.matched.preferences")+usersMatchScoreMap.get(usersDetails.getEmailId()));
				roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("separator"));
			}
		}
		userGroupFormation.GroupFormation();
		validation = true;
	}

	public boolean getValidation(){
		return validation;
	}

}
