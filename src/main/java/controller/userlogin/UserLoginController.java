package controller.userlogin;

import Exception.EmailException;
import Exception.PasswordNotMatchException;
import controller.clicommentlist.IMakeCLICommentListController;
import controller.ClassInitializer;
import controller.usergroupformationcontroller.UserGroupFormation;
import controller.verifications.IEmailFormatVerfier;
import controller.verifications.IEmailVerifierController;

public class UserLoginController implements IUserLoginController {

	public static String emailId;
	String email;
	String password;
	String checkCreds;

	public void userLoginController() throws  EmailException, PasswordNotMatchException {
		try {
			Login();
		}catch (Exception e){
			e.printStackTrace();
			Login();
		}
	}

	public String validatecheckCreds(String email){
		this.email=emailId;
		return email;
	}

	public void Login() throws PasswordNotMatchException, EmailException {
		IMakeCLICommentListController makeCLICommentListController=ClassInitializer.initializer().getIMakeCLICommentListController();
		IGiveCredintials giveCredintials=ClassInitializer.initializer().getIGiveCredintials();
		ICheckCredentials checkCredentials=ClassInitializer.initializer().getICheckCredentials();
		IEmailFormatVerfier emailFormatVerfier=ClassInitializer.initializer().getIEmailFormatVerfier();
		IEmailVerifierController emailVerifierController=ClassInitializer.initializer().getIEmailVerifierController();
		IAfterCheckingCredintials afterCheckingCredintials=ClassInitializer.initializer().getIAfterCheckingCredintials();
		IPasswordValidity passwordValidity=ClassInitializer.initializer().getIPasswordValidity();

		afterCheckingCredintials.afterCheckingSuccessfullCredentials();
		email=giveCredintials.getEmail();
		emailFormatVerfier.emailFormatVerifier(email);
		emailVerifierController.emailDoesNotExists(email,checkCredentials.checkCredentials());
		makeCLICommentListController.makeCLICommentListController("login.password.message");
		checkCreds=checkCredentials.checkCredentials();
		password=giveCredintials.getPassword();
		passwordValidity.getPasswordvalilidity(checkCreds,email,password);
	}
}
