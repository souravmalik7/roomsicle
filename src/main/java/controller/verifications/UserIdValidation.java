package controller.verifications;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.clicommentlist.IMakeCLICommentListController;

public class UserIdValidation implements IUserIdValidation{
    public String userIdValidation(int userId){
        IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        String identity;
        if (userId==1){
            identity=CommandLineInputProperties.getCommandLineInputPropertyValue("welcomepage.roommate.identify.message");
            iMakeCLICommentListController.makeCLICommentListController("welcomepage.roommate.identify.message");
            return identity;
        }
        else if(userId==2){
            identity=CommandLineInputProperties.getCommandLineInputPropertyValue("welcomepage.owner.identify.message");
            iMakeCLICommentListController.makeCLICommentListController("welcomepage.owner.identify.message");
            return identity;
        }
        else {
            identity=CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.invalid.input.message");
            iMakeCLICommentListController.makeCLICommentListController("owner.survey.invalid.input.message");
        }
        return identity;
    }
}
