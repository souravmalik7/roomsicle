package controller.verifications;

import Exception.PasswordNotMatchException;
import commandline.CommandLineInputProperties;
import controller.clicommentlist.IMakeCLICommentListController;
import controller.ClassInitializer;

public class PasswordVerifierController implements IPasswordVerifierController {
     public void passwordVerifierController(String password, String confirmPassword) throws PasswordNotMatchException {
      IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
       if (password.equals(confirmPassword)){
           iMakeCLICommentListController.makeCLICommentListController("registration.password.match.message");
       }else {
           throw new PasswordNotMatchException(CommandLineInputProperties.getCommandLineInputPropertyValue("registration.password.do.not.match.message"));
       }
    }

    public String passwordVerifierValidatorController(String password, String confirmPassword) {
        String message;
        IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        if (password.equals(confirmPassword)){
            message=iMakeCLICommentListController.makeCLICommentListController("registration.password.match.message").toString();
        }else {
            message=iMakeCLICommentListController.makeCLICommentListController("registration.password.do.not.match.message").toString();
        }
        return message;
    }
}
