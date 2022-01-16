package controller;

import commandline.IRoomsicleCLI;
import controller.userprofile.IUserHomePageController;
import java.util.InputMismatchException;

public class Navigator {

    public void navigator()  {

        IRoomsicleCLI roomsicleCLI;
        int userInput;

        roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();

        try {
            roomsicleCLI.printMessage("");
            roomsicleCLI.printMessage(ControllerProperties.getControllerPropertyValue("navigation.option.message"));
            userInput = roomsicleCLI.getNumberResponse();

            if (userInput == 1) {
                IUserHomePageController userHomePageController;
                userHomePageController = ClassInitializer.initializer().getIUserHomePageController();

                userHomePageController.showUserHomePageController();
            } else if (userInput == 2) {
                System.exit(0);
            } else {
                roomsicleCLI.printMessage("invalid.entry.message");
                navigator();
            }
        }catch(InputMismatchException e){
            roomsicleCLI.printMessage("invalid.entry.message");
            navigator();
        }

    }
}
