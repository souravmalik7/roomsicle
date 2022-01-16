package controller.userprofile;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;

public class RoommateDetails implements IRoommateDetails{
    public void roommateDetails(String firstName,String secondName,String thirdName){
        IRoomsicleCLI roomsicleCLI= ClassInitializer.initializer().getRoomsicleCLI();
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.second.roommate.email.message")+firstName);
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.third.roommate.email.message")+secondName);
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.fourth.roommate.email.message")+thirdName);
    }
}
