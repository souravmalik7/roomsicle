package controller.propertybidding;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import commandline.RoomsicleCLI;
import controller.ClassInitializer;
import database.propertybiddingdao.IPropertyDetailsDAO;
import database.propertybiddingdao.IPropertyOwnersDAO;
import database.propertybiddingdao.PropertyDetailsDAO;
import database.propertybiddingdao.PropertyOwnersDAO;
import models.biddingmodels.PropertyDetailsModel;
import models.biddingmodels.PropertyOwnerModel;

import java.util.ArrayList;

public class AvailableProperties implements IAvailableProperties{

    boolean validation = false;

    //public static void main(String args[]) {
    public void displayProperties(){
        IRoomsicleCLI roomsicleCLI;
        IPropertyOwnersDAO propertiesOwners;
        ArrayList<PropertyOwnerModel> listOfPropertyOwners;
        IPropertyDetailsDAO propertiesDetails;
        ArrayList<PropertyDetailsModel> listOfPropertyDetails;

        roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        propertiesOwners = ClassInitializer.initializer().getPropertyOwnersDAO();
        listOfPropertyOwners = propertiesOwners.getPropertyOwnerDetails();
        propertiesDetails = ClassInitializer.initializer().getPropertyDetailsDAO();
        listOfPropertyDetails = propertiesDetails.getPropertyDetails();


        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("properties.display.page.opening"));
        for(PropertyDetailsModel propertyDetailsObject : listOfPropertyDetails) {
            for(PropertyOwnerModel propertyOwnerObject : listOfPropertyOwners){
                if(propertyOwnerObject.getEmailId().equals(propertyDetailsObject.getOwnerId())){
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("property.owner.display.firstname")+propertyOwnerObject.getFirstName());
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("property.owner.display.contactnumber")+propertyOwnerObject.getContactNumber());
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("property.owner.display.emailid")+propertyOwnerObject.getEmailId());
                    break;
                }
            }
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("property.details.display.propertyid")+propertyDetailsObject.getPropertyId());
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("property.details.display.propertyrent")+(propertyDetailsObject.getPropertyPrice()));
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("property.details.display.individualrent")+(propertyDetailsObject.getPropertyPrice()/4));
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("separator"));
            validation = true;
        }

    }

    public boolean getValidation(){
        return validation;
    }


}
