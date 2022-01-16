package controller.verifications;

import Exception.EmailException;
import commandline.CommandLineInputProperties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailFormatVerifierController implements IEmailFormatVerfier {
    public void emailFormatVerifier(String email) throws EmailException {
        if (validEmailFormatVerifier(email) == false ) {
            throw new EmailException(CommandLineInputProperties.getCommandLineInputPropertyValue("login.email.verify.wrong"));
        }
    }

    @Override
    public boolean validEmailFormatVerifier(String email) {
        Pattern pattern;
        Matcher matcher;
        String regex = CommandLineInputProperties.getCommandLineInputPropertyValue("login.email.id.verify");
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(email);
        if (matcher.matches()==false ){
            return false;
        }
        else{
            return true;
        }
    }
}
