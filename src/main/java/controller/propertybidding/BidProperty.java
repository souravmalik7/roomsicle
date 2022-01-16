package controller.propertybidding;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import Exception.InvalidBidException;
import controller.ClassInitializer;
import controller.ControllerProperties;
import database.propertybiddingdao.*;
import models.biddingmodels.BiddingDetailsModel;
import models.biddingmodels.IBiddingDetailsModel;
import models.biddingmodels.PropertyDetailsModel;

import java.util.ArrayList;

public class BidProperty implements IBidProperty{

    boolean validation;

    public void bidProperty() throws InvalidBidException {

            IPropertyBidderDAO propertyBidderDAO;
            IRoomsicleCLI roomsicleCLI;
            IPropertyDetailsDAO propertiesDetails;
            ArrayList<PropertyDetailsModel> listOfPropertyDetails;
            IAvailableProperties availableProperties;
            ArrayList<BiddingDetailsModel> listOfPropertyBidders;
            IBiddingDAO biddingDAO;
            int propertyIdSelected;
            IBiddingDetailsModel bidObject;
            int propertyBidded;

            propertiesDetails = ClassInitializer.initializer().getPropertyDetailsDAO();
            listOfPropertyDetails = propertiesDetails.getPropertyDetails();
            availableProperties = ClassInitializer.initializer().getAvailableProperties();
            availableProperties.displayProperties();
            propertyBidderDAO = ClassInitializer.initializer().getPropertyBidderDAO();
            listOfPropertyBidders = propertyBidderDAO.getPropertyBidDetails();
            biddingDAO = ClassInitializer.initializer().getBiddingDAO();
            roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();


            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("enter.property.id.message"));
            propertyIdSelected = roomsicleCLI.getNumberResponse();

            bidObject = ClassInitializer.initializer().getBiddingDetailsModel();
            propertyBidded = 0;
            BiddingDetailsModel selectedPropertyForBid = new BiddingDetailsModel();

            for (PropertyDetailsModel propertyDetailsObject : listOfPropertyDetails) {

                if (propertyDetailsObject.getPropertyId().equals(String.valueOf(Integer.valueOf(propertyIdSelected)))) {
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("property.details.display.propertyid") + propertyDetailsObject.getPropertyId());
                    roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("property.details.display.individualrent") + (propertyDetailsObject.getPropertyPrice() / 4));

                    for (BiddingDetailsModel biddingDetailsObject : listOfPropertyBidders) {
                        if (biddingDetailsObject.getPropertyId().equals(propertyDetailsObject.getPropertyId())) {
                            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bid.price") + biddingDetailsObject.getBid());
                            propertyBidded = 1;
                            selectedPropertyForBid.setBid(biddingDetailsObject.getBid());
                            selectedPropertyForBid.setPropertyId(String.valueOf(Integer.valueOf(propertyIdSelected)));
                            selectedPropertyForBid.setUserEmailId(ControllerProperties.getControllerPropertyValue("loggedInUser"));
                        }
                    }
                    if (propertyBidded == 0) {
                        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bid.price.nobids"));
                    }
                }
            }
            roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("enter.bid.message"));
            bidObject.setBid(roomsicleCLI.getNumberResponse());
            if (propertyBidded == 0 || (propertyBidded == 1 && bidObject.getBid() > selectedPropertyForBid.getBid())) {
                for (PropertyDetailsModel propertyDetailsObject : listOfPropertyDetails) {
                    if (propertyDetailsObject.getPropertyId().equals(String.valueOf(Integer.valueOf(propertyIdSelected)))) {
                        bidObject.setPropertyId(propertyDetailsObject.getPropertyId());
                        bidObject.setUserEmailId(ControllerProperties.getControllerPropertyValue("loggedInUser"));
                        biddingDAO.enterBid(bidObject);
                        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bid.placed"));
                        ClassInitializer.initializer().getNavigator().navigator();
                        validation = true;
                        break;
                    }
                }
            }
            else {
                roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("bid.error.message"));
                ClassInitializer.initializer().getNavigator().navigator();
                validation = true;
            }
    }

    public boolean getValidation(){
        return validation;
    }

}
