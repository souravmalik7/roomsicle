package controller.bestfitroommates;

import controller.getloggedinuser.IGetLoggedInUserController;

import java.util.HashMap;

public interface IBestFitRoommateController {

	HashMap<String, Integer> findBestFit(IGetLoggedInUserController getLoggedInUserObject);
	
}
