package controller.verifications;

import Exception.EmailException;
import commandline.CommandLineInputProperties;
import controller.ClassInitializer;
import database.userlogindao.IUserLoginDAO;

public class EmailVerfierController implements IEmailVerifierController {


    public void userEmailAlreadyRegistered(String emailId, String userData)  throws EmailException {
        IUserLoginDAO userLoginDAO= ClassInitializer.initializer().getIUserLoginDAO();
        for(String Email:userLoginDAO.getUserLoginAndPassword(userData).keySet()){
           if (Email.equals(emailId)){
               throw new EmailException(CommandLineInputProperties.getCommandLineInputPropertyValue("registration.email.exists.message"));
           }
             }
    }
    public void emailDoesNotExists(String emailId, String userData) throws EmailException {
        IUserLoginDAO userLoginDAO= ClassInitializer.initializer().getIUserLoginDAO();
        if (userLoginDAO.getUserLoginAndPassword(userData).containsKey(emailId)){
                CommandLineInputProperties.getCommandLineInputPropertyValue("registration.identify.correct.email.id.message");
                 }
            else{
                throw new EmailException(CommandLineInputProperties.getCommandLineInputPropertyValue("login.fail.message"));
            }
        }

    public String validateEmailExists(String emailId, String userData){
        IUserLoginDAO userLoginDAO= ClassInitializer.initializer().getIUserLoginDAO();
        String emailMessage;
        if (userLoginDAO.getUserLoginAndPassword(userData).containsKey(emailId)){
            emailMessage=CommandLineInputProperties.getCommandLineInputPropertyValue("registration.identify.correct.email.id.message");
        }else {
            emailMessage= CommandLineInputProperties.getCommandLineInputPropertyValue("login.fail.message");
        }
        return emailMessage;
    }
}

