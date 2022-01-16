package controller.userlogin;

import controller.ClassInitializer;
import controller.userprofile.IUserHomePageController;
import database.surveytakendao.ISurveyTakenDAO;

import static controller.userregistration.UserRegistrationConstants.ONESTRING;
import static controller.userregistration.UserRegistrationConstants.ZEROSTRING;

public class CheckSurveyTaken implements ICheckSurveyTaken {
    public void checkSurveyTaken(String email)  {
        String getUserData;
        String survey;
        IUserHomePageController userHomePageController=ClassInitializer.initializer().getIUserHomePageController();
        ICheckCredentials checkCredentials=ClassInitializer.initializer().getICheckCredentials();
        ISurveyTakenDAO surveyTakenDAO=ClassInitializer.initializer().getISurveyTakenDAO();
        getUserData=checkCredentials.checkCredentials();
        survey=surveyTakenDAO.getSurveyTaken(getUserData).get(email);
        if (survey.equals(ONESTRING)){
            userHomePageController.showUserHomePageController();
        }
        else if (survey.equals(ZEROSTRING)){
            ClassInitializer.initializer().getIUserRegistrationController().getToTheSurvey();
        }
    }
}
