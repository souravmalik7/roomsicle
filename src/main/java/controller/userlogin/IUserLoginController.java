package controller.userlogin;

import Exception.EmailException;
import Exception.PasswordNotMatchException;


public interface IUserLoginController {
    void userLoginController() throws  EmailException, PasswordNotMatchException;
    void Login() throws PasswordNotMatchException, EmailException;
    String validatecheckCreds(String email);
}
