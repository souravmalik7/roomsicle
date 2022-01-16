package controller.userlogin;

import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import controller.clicommentlist.IMakeCLICommentListController;
import database.userlogindao.IUserLoginDAO;
import Exception.PasswordNotMatchException;

public class PasswordValidity implements IPasswordValidity{
    public void getPasswordvalilidity(String checkCreds,String email,String password) throws PasswordNotMatchException {
        IUserLoginDAO userLoginDAO= ClassInitializer.initializer().getIUserLoginDAO();
        IMakeCLICommentListController makeCLICommentListController=ClassInitializer.initializer().getIMakeCLICommentListController();
        ICheckSurveyTaken checkSurveyTaken=ClassInitializer.initializer().getICheckSurveyTaken();
        if (email == null) {
            throw new PasswordNotMatchException(CommandLineInputProperties.getCommandLineInputPropertyValue("login.fail.email.id.null.message"));
        }
        else{
            if(userLoginDAO.getUserLoginAndPassword(checkCreds).get(email).equals(password) ) {
                makeCLICommentListController.makeCLICommentListController("login.successfull.message");
                checkSurveyTaken.checkSurveyTaken(email);
            }
            else {
                throw new PasswordNotMatchException(CommandLineInputProperties.getCommandLineInputPropertyValue("login.email.password.verify.wrong"));
            }
        }
    }

    public String validatePassword(String email,String checkCreds){
        IUserLoginDAO userLoginDAO= ClassInitializer.initializer().getIUserLoginDAO();
        String password;
        password=userLoginDAO.getUserLoginAndPassword(checkCreds).get(email);
        return password;
    }
}
