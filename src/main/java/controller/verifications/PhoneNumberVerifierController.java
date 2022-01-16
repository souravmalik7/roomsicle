package controller.verifications;

import Exception.PhoneNumberException;
import commandline.CommandLineInputProperties;
import controller.clicommentlist.IMakeCLICommentListController;
import controller.ClassInitializer;

public class PhoneNumberVerifierController implements IPhoneNumberVerifierController {
    public void phoneNumberVerifierController(long contact) throws PhoneNumberException {
        IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        if (String.valueOf(contact).length() == 10) {
            iMakeCLICommentListController.makeCLICommentListController("registration.identify.correct.contact.number.message");
        } else {
            throw new PhoneNumberException(CommandLineInputProperties.getCommandLineInputPropertyValue("registration.identify.incorrect.contact.number.message"));
        }
    }
}
