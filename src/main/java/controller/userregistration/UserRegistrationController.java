package controller.userregistration;

import Exception.InvalidInputException;
import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ControllerProperties;
import controller.clicommentlist.IMakeCLICommentListController;
import controller.ClassInitializer;
import controller.verifications.IEmailFormatVerfier;
import controller.verifications.IEmailVerifierController;
import controller.verifications.IPasswordVerifierController;
import controller.verifications.IPhoneNumberVerifierController;
import database.DatabaseQueryProperties;
import database.userregistrationdao.IUserRegistrationDAO;
import models.usermodel.IUsersModel;

import static controller.userregistration.UserRegistrationConstants.*;
import static controller.usersurvey.UserSurveyConstants.ONE;
import static controller.usersurvey.UserSurveyConstants.TWO;
import static controller.welcomepage.WelcomePageController.userId;


public class UserRegistrationController implements IUserRegistrationController {
    public static String emailId;
    String firstName;
    String lastName;
    long contact;
    String email;
    String password;
    String confirmPassword;
    String userData;
    String setType;


    public void userRegistrationController()  {
        try{
            userRegistrationCheck();
        }
        catch (Exception e){
            e.printStackTrace();
            userRegistrationCheck();
        }
    }

    public void userRegistrationCheck(){
        IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        IUserRegistrationDAO iUserRegistrationDAO=ClassInitializer.initializer().getIUserRegistrationDAO();
        IUsersModel iUsersModel=ClassInitializer.initializer().getIUsersModel();
        iMakeCLICommentListController.makeCLICommentListController("welcomepage.add.message","registration.main.message","welcomepage.add.message");
        setType();
        setFirstName();
        setLastName();
        setContact();
        setEmail();
        setPassword();
        iUserRegistrationDAO.userRegistration(iUsersModel);
        iMakeCLICommentListController.makeCLICommentListController("registration.successful.message");
        getToTheSurvey();
    }
    public void setType() {
        IUsersModel iUsersModel=ClassInitializer.initializer().getIUsersModel();
        try {
            if (userId==1){
                setType=USER;
            }
            else if(userId==2){
                setType=OWNER;
            }
            else{
                throw new InvalidInputException(CommandLineInputProperties.getCommandLineInputPropertyValue("owner.survey.invalid.input.message"));
            }
            iUsersModel.setType(setType);
        }catch (Exception e) {
            e.printStackTrace();
            setType();
        }
    }

    public void setFirstName() {
        IRoomsicleCLI iRoomsicleCLI=ClassInitializer.initializer().getRoomsicleCLI();
        IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        IUsersModel iUsersModel=ClassInitializer.initializer().getIUsersModel();

        try {
            iMakeCLICommentListController.makeCLICommentListController("registration.identify.your.first.name.message");
            firstName = iRoomsicleCLI.getStringResponse();
            iUsersModel.setFirstName(firstName);
    }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLastName() {
        IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        IUsersModel iUsersModel=ClassInitializer.initializer().getIUsersModel();
        IRoomsicleCLI iRoomsicleCLI=ClassInitializer.initializer().getRoomsicleCLI();
        try {
            iMakeCLICommentListController.makeCLICommentListController("registration.identify.your.last.name.message");
            lastName = iRoomsicleCLI.getStringResponse();
            iUsersModel.setLastName(lastName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setContact() {
        try {
            IRoomsicleCLI iRoomsicleCLI=ClassInitializer.initializer().getRoomsicleCLI();
            IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
            IUsersModel iUsersModel=ClassInitializer.initializer().getIUsersModel();
            IPhoneNumberVerifierController iPhoneNumberVerifierController=ClassInitializer.initializer().getIPhoneNumberVerifierController();
            iMakeCLICommentListController.makeCLICommentListController("registration.identify.your.contact.number.message");
            contact = iRoomsicleCLI.getLongNumberResponse();
            iPhoneNumberVerifierController.phoneNumberVerifierController(contact);
            iUsersModel.setContactNumber(contact);
        }
        catch (Exception e) {
            e.printStackTrace();
            setContact();
        }
    }
    public void setEmail() {
        IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        IUsersModel iUsersModel=ClassInitializer.initializer().getIUsersModel();
        IEmailVerifierController iEmailVerifierController=ClassInitializer.initializer().getIEmailVerifierController();
        IEmailFormatVerfier iEmailFormatVerfier=ClassInitializer.initializer().getIEmailFormatVerfier();
        IRoomsicleCLI iRoomsicleCLI=ClassInitializer.initializer().getRoomsicleCLI();
        try {
            iMakeCLICommentListController.makeCLICommentListController("registration.identify.your.email.id.message");
            email = iRoomsicleCLI.getStringResponse();
            if (userId==1) {
                userData= DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.login.email.password.query");
            }
            else if (userId==2) {
                userData= DatabaseQueryProperties.getDatabaseQueryPropertyValue("owner.login.email.password.query");
            }
            iEmailVerifierController.userEmailAlreadyRegistered(email,userData);
            iEmailFormatVerfier.emailFormatVerifier(email);
            ControllerProperties.setControllerPropertyValue("user.logged.in.email.id",email);
            emailId=email;
            iUsersModel.setEmailId(email);
        }
        catch (Exception e) {
            e.printStackTrace();
            setEmail();
        }
    }
    public void setPassword() {
        IRoomsicleCLI iRoomsicleCLI=ClassInitializer.initializer().getRoomsicleCLI();
        IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        IUsersModel iUsersModel=ClassInitializer.initializer().getIUsersModel();
        IPasswordVerifierController iPasswordVerifierController=ClassInitializer.initializer().getIPasswordVerifierController();
        try {
            iMakeCLICommentListController.makeCLICommentListController("registration.identify.your.password.message");
            password = iRoomsicleCLI.getStringResponse();
            iUsersModel.setPassword(password);
            iMakeCLICommentListController.makeCLICommentListController("registration.identify.your.password.confirm.message");
            confirmPassword = iRoomsicleCLI.getStringResponse();
            iPasswordVerifierController.passwordVerifierController(password, confirmPassword);
        }
        catch (Exception e) {
            e.printStackTrace();
            setPassword();
        }

    }

    public void getToTheSurvey(){
        try {
            if (userId==ONE){
                ClassInitializer.initializer().getUserSurveyMain().takeUserSurvey();
            }
            else if(userId==TWO){
                ClassInitializer.initializer().getOwnerSurveyMain().takeOwnerSurvey();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
