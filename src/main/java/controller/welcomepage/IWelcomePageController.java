package controller.welcomepage;

import Exception.EmailException;
import Exception.InvalidInputException;
import Exception.PasswordNotMatchException;

public interface IWelcomePageController {
     void  showWelcomePage();
     void userSelection() throws InvalidInputException, EmailException, PasswordNotMatchException;
}
