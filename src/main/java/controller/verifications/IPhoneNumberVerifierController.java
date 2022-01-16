package controller.verifications;

import Exception.PhoneNumberException;

public interface IPhoneNumberVerifierController {
    void phoneNumberVerifierController(long contact) throws PhoneNumberException;
}
