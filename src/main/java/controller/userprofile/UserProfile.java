package controller.userprofile;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import controller.ControllerProperties;
import database.fitroommatesdao.IUserDetailsDAO;
import models.fitroommatemodels.UserDetailsModel;
import database.getusersgroupdao.IGetUserGroupDAO;
import models.*;

import java.util.ArrayList;


public class UserProfile implements IUserProfile{
    public void userProfile(){
        IRoomsicleCLI roomsicleCLI=ClassInitializer.initializer().getRoomsicleCLI();
        String loggedInUserId;
        loggedInUserId=ControllerProperties.getControllerPropertyValue("user.logged.in.email.id");
        IUserDetailsDAO userDetailsDAO=ClassInitializer.initializer().getIUserDetailsDAO();
        IGetUserGroupDAO getUserGroupDAO=ClassInitializer.initializer().getUsersGroupDAO();
        ArrayList<UserDetailsModel> listOfUserDetails;
        listOfUserDetails = userDetailsDAO.getUserDetails();
       IRoommateDetails roommateDetails=ClassInitializer.initializer().getRoommateDetails();

        ArrayList<IUsersGroupModel> listOfGroupDetails;
        listOfGroupDetails = getUserGroupDAO.getUsersGroup();
       for(UserDetailsModel userDetailsObject : listOfUserDetails) {
            if(userDetailsObject.getEmailId().equals(loggedInUserId) ){
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bestfit.roommate.display.user.firstname")+userDetailsObject.getFirstName());
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bestfit.roommate.display.user.lastname")+userDetailsObject.getLastName());
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bestfit.roommate.display.user.contactnumber")+userDetailsObject.getContactNumber());
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bestfit.roommate.display.user.emailid")+userDetailsObject.getEmailId());
            }
        }
       for(IUsersGroupModel usersGroupModel : listOfGroupDetails) {
            if(usersGroupModel.getFirstName().equals(loggedInUserId) ){
                roommateDetails.roommateDetails(usersGroupModel.getSecondName(),usersGroupModel.getThirdName(),usersGroupModel.getFourthName());
            }
            else if(usersGroupModel.getSecondName().equals(loggedInUserId) ){
                roommateDetails.roommateDetails(usersGroupModel.getFirstName(),usersGroupModel.getThirdName(),usersGroupModel.getFourthName());
            }
            else if(usersGroupModel.getThirdName().equals(loggedInUserId) ){
                roommateDetails.roommateDetails(usersGroupModel.getSecondName(),usersGroupModel.getFirstName(),usersGroupModel.getFourthName());
            }
            else if(usersGroupModel.getFourthName().equals(loggedInUserId) ){
                roommateDetails.roommateDetails(usersGroupModel.getSecondName(),usersGroupModel.getThirdName(),usersGroupModel.getFirstName());
            }
        }
        ClassInitializer.initializer().getNavigator().navigator();

    }
}
