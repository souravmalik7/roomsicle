package controller.verifications;

import Exception.PasswordNotMatchException;

public interface IPasswordVerifierController {
    void passwordVerifierController(String password, String confirmPassword) throws PasswordNotMatchException;
    String passwordVerifierValidatorController(String password, String confirmPassword);
}
