package controller.userlogin;

import Exception.PasswordNotMatchException;

public interface IPasswordValidity {
     void getPasswordvalilidity(String checkCreds, String email, String password) throws PasswordNotMatchException;
     String validatePassword(String email,String checkCreds);
}
