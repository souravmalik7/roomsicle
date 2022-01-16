package controller.userlogin;

import controller.ClassInitializer;
import controller.ControllerProperties;
import database.DatabaseQueryProperties;

import static controller.filterroommates.FilterRoommatesInputConstants.ONE;
import static controller.filterroommates.FilterRoommatesInputConstants.TWO;
import static controller.welcomepage.WelcomePageController.userId;

public class CheckCredentials implements ICheckCredentials{
    public String checkCredentials()  {
        String getUserData;
         getUserData=null;
        if (userId==ONE) {
            getUserData = DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.login.email.password.query");
        }else if (userId==TWO){
            getUserData = DatabaseQueryProperties.getDatabaseQueryPropertyValue("owner.login.email.password.query");
        }
        return getUserData;
    }
}
