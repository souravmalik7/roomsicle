package controller.verifications;
import Exception.EmailException;

public interface IEmailFormatVerfier{
        void emailFormatVerifier(String email) throws EmailException;
        boolean validEmailFormatVerifier(String email) ;
    }


