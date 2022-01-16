package controller.userlogin;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.clicommentlist.IMakeCLICommentListController;

public class AfterCheckingCredintials implements IAfterCheckingCredintials{
    public String afterCheckingSuccessfullCredentials(){
        IMakeCLICommentListController makeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        makeCLICommentListController.makeCLICommentListController("welcomepage.add.message"
                , "login.main.message", "welcomepage.add.message"
                ,"login.email.id.message");
        return CommandLineInputProperties.getCommandLineInputPropertyValue("login.successfull.message");
    }
}
