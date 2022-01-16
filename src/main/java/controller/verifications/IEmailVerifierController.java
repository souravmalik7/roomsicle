package controller.verifications;
import Exception.EmailException;

public interface IEmailVerifierController {
    void userEmailAlreadyRegistered(String emailId, String userData) throws EmailException;
    void emailDoesNotExists(String emailId, String userData) throws EmailException;
}
