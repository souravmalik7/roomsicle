package controller.userprofile;

import commandline.IRoomsicleCLI;
import controller.bestfitroommates.IBestFitRoommateController;
import controller.bestfitroommates.IBestFitRoommatesDisplayController;
import controller.checkoutproperty.ICheckoutProperties;
import controller.checkoutproperty.ICheckoutPropertiesDisplayController;
import controller.clicommentlist.IMakeCLICommentListController;
import controller.ClassInitializer;
import controller.filterroommates.IFilterRoommates;
import controller.filterroommates.IFilterRoommatesDisplayController;
import controller.ownerprofile.IOwnerProfile;
import controller.propertybidding.IBidProperty;
import controller.welcomepage.IWelcomePageController;
import controller.userlogin.IUserLoginController;
import Exception.InvalidBidException;

import static controller.filterroommates.FilterRoommatesInputConstants.ONE;
import static controller.filterroommates.FilterRoommatesInputConstants.TWO;
import static controller.welcomepage.WelcomePageController.userId;

public class UserHomePageController implements IUserHomePageController {
    public void showUserHomePageController()  {
        int userInput;
        IBestFitRoommatesDisplayController bestFitRoommatesDisplayController;
        IBestFitRoommateController bestFitRoommateController;
        IFilterRoommates filterRoommates;
        IFilterRoommatesDisplayController filterRoommatesDisplayController;
        IBidProperty bidProperty;
        IUserLoginController userLoginController = ClassInitializer.initializer().getUserLoginController();
        IMakeCLICommentListController makeCLICommentListController = ClassInitializer.initializer().getIMakeCLICommentListController();
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();
        IWelcomePageController welcomePageController = ClassInitializer.initializer().getIWelcomePageController();
        IUserProfile userProfile = ClassInitializer.initializer().getUserProfile();
        IOwnerProfile ownerProfile = ClassInitializer.initializer().getOwnerProfile();
        bestFitRoommatesDisplayController = ClassInitializer.initializer().getBestFitRoommatesDisplayController();
        bestFitRoommateController = ClassInitializer.initializer().getBestFitRoommateController();
        filterRoommates = ClassInitializer.initializer().getFilterRoommates();
        filterRoommatesDisplayController = ClassInitializer.initializer().getFilterRoommatesDisplayController();
        bidProperty = ClassInitializer.initializer().getBidProperty();
        ICheckoutProperties checkoutProperties = ClassInitializer.initializer().getCheckoutProperties();
        ICheckoutPropertiesDisplayController checkoutPropertiesDisplayController = ClassInitializer.initializer().getCheckoutPropertiesDisplayController();

        if (userId == ONE) {
            makeCLICommentListController.makeCLICommentListController("welcomepage.add.message"
                    , "user.home.page.main.message", "welcomepage.add.message"
                    , "user.home.page.option.message", "user.home.page.my.profile.option.message",
                    "user.home.page.my.best.git.option.message", "user.home.page.filter.roommate.option.message",
                    "user.home.page.system.suggested.properties.option.message","user.home.page.system.checkout.properties.option.message",
                    "user.home.page.expense.management.option.message", "user.home.page.system.bidding.properties.option.message","user.home.page.logout.option.message"
            );
        } else if (userId == TWO) {
            makeCLICommentListController.makeCLICommentListController("owner.profile.show");
            ClassInitializer.initializer().getOwnerProfile().ownerProfile();
        }
        userInput = roomsicleCLI.getNumberResponse();

        switch (userInput) {
            case 1:
                if (userId == ONE) {
                    userProfile.userProfile();
                } else if (userId == TWO) {
                    ownerProfile.ownerProfile();
                }
                break;
            case 2:
                bestFitRoommatesDisplayController.getBestFits(bestFitRoommateController);
                break;
            case 3:
                filterRoommatesDisplayController.getFilteredFits(filterRoommates);
                break;
            case 4:
                ClassInitializer.initializer().getSystemGeneratedProperties().initializeSystemGeneratedProperties();
                break;
            case 5:
                checkoutPropertiesDisplayController.getCheckedOutProperties(checkoutProperties);
                break;
            case 6:
                ClassInitializer.initializer().getExpenseManagementHomePageController().showHomePage();
                break;
            case 7:
                try {
                    bidProperty.bidProperty();
                } catch (InvalidBidException e) {
                    e.printStackTrace();
                }
                break;
            case 8:
                makeCLICommentListController.makeCLICommentListController("logged.out.successfully.message");
                welcomePageController.showWelcomePage();
                break;
            default:
                ClassInitializer.initializer().getNavigator();
        }
    }
}

