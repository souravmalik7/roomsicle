package controller.userlogin;

import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import controller.ControllerProperties;

public class GiveCredintials implements IGiveCredintials{

    public String getEmail(){
        IRoomsicleCLI roomsicleCLI=ClassInitializer.initializer().getRoomsicleCLI();
        String email;
        email = roomsicleCLI.getStringResponse();
        ControllerProperties.setControllerPropertyValue("user.logged.in.email.id",email);
        return ControllerProperties.getControllerPropertyValue("user.logged.in.email.id");
    }

    public String getPassword(){
        IRoomsicleCLI roomsicleCLI=ClassInitializer.initializer().getRoomsicleCLI();
        String password;
        password = roomsicleCLI.getStringResponse();
        return password;
    }
}
