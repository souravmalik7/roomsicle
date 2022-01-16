package controller.ownerprofile;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import controller.ControllerProperties;
import database.ownerpropertydetailsdao.IOwnerPropertyDetailsDAO;
import database.ownerdetailsdao.IOwnersDetailsDAO;
import models.ownerdetailsmodel.IOwnerDetailsModel;
import models.ownerpropertydetailsmodel.IOwnerPropertyDetailsModel;

import java.util.ArrayList;

public class OwnerProfile implements IOwnerProfile {

    public void ownerProfile(){
        IRoomsicleCLI iRoomsicleCLI=ClassInitializer.initializer().getRoomsicleCLI();
        IOwnerPropertyDetailsDAO ownerPropertyDetailsDAO=ClassInitializer.initializer().getIOwnerPropertyDetailsDAO();
        IOwnersDetailsDAO ownersDetailsDAO=ClassInitializer.initializer().getIOwnersDetailsDAO();
        String loggedInUserId;
        loggedInUserId=ControllerProperties.getControllerPropertyValue("user.logged.in.email.id");
        ArrayList<IOwnerDetailsModel> listOfOwnerDetails;
        ArrayList<IOwnerPropertyDetailsModel> listofOwnerPropertyDetails;
        listOfOwnerDetails = ownersDetailsDAO.getOwnersDetails();
        listofOwnerPropertyDetails= ownerPropertyDetailsDAO.getOwnersPropertyDetails();

        for(IOwnerDetailsModel ownerDetailsModel : listOfOwnerDetails) {
            if(ownerDetailsModel.getEmailId().equals(loggedInUserId) ){
                iRoomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bestfit.roommate.display.user.firstname")+ownerDetailsModel.getFirstName());
                iRoomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bestfit.roommate.display.user.lastname")+ownerDetailsModel.getLastName());
                iRoomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bestfit.roommate.display.user.contactnumber")+ownerDetailsModel.getContactNumber());
                iRoomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bestfit.roommate.display.user.emailid")+ownerDetailsModel.getEmailId());
            }
        }
        for(IOwnerPropertyDetailsModel ownerPropertyDetailsObject : listofOwnerPropertyDetails) {
            if(ownerPropertyDetailsObject.getOwnerId().equals(loggedInUserId) ){
                iRoomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.property.address.message")+ownerPropertyDetailsObject.getAddress());
                iRoomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.property.price.message")+ownerPropertyDetailsObject.getPrice());
                }
        }
    }
}
