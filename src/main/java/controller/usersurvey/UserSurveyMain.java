package controller.usersurvey;

import commandline.CommandLineInputProperties;
import commandline.IRoomsicleCLI;
import controller.ClassInitializer;
import controller.ControllerProperties;
import database.usersurveydao.IUserSurveyDAO;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserSurveyMain {

    static final Logger logger = LogManager.getLogger(UserRoommateGenderPreference.class);

    //take user survey
    public void takeUserSurvey() {
        IUserSurvey userAlcoholHabits = ClassInitializer.initializer().getUserAlcoholHabits();
        IUserSurvey userBudget = ClassInitializer.initializer().getUserBudget();
        IUserSurvey userFoodHabits = ClassInitializer.initializer().getUserFoodHabits();
        IUserSurvey userGender = ClassInitializer.initializer().getUserGender();
        IUserSurvey userRoommateAlcoholHabitsPreference = ClassInitializer.initializer().getUserRoommateAlcoholHabitsPreference();
        IUserSurvey userRoommateFoodHabitsPreference = ClassInitializer.initializer().getUserRoommateFoodHabitsPreference();
        IUserSurvey userRoommateGenderPreference = ClassInitializer.initializer().getUserRoommateGenderPreference();
        IUserSurvey userRoommateSmokingHabitsPreference = ClassInitializer.initializer().getUserRoommateSmokingHabitsPreference();
        IUserSurvey userSmokingHabits = ClassInitializer.initializer().getUserSmokingHabits();
        IUserSurvey userUniversityDistancePreference = ClassInitializer.initializer().getUserUniversityDistancePreference();
        UserSurveyModel userSurveyModel = ClassInitializer.initializer().getUserSurveyModel();
        IUserSurveyDAO userSurveyDAO = ClassInitializer.initializer().getUserSurveyDAO();
        IRoomsicleCLI roomsicleCLI = ClassInitializer.initializer().getRoomsicleCLI();

        //get logged-in user email id and set it in model
        userSurveyModel.setUserId(ControllerProperties.getControllerPropertyValue("user.logged.in.email.id"));

        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.take.survey.message"));
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.personal.details.message"));
        //get user gender value
        userGender.getValue(userSurveyModel);
        //get user food habits value
        userFoodHabits.getValue(userSurveyModel);
        //get user smoking habits value
        userSmokingHabits.getValue(userSurveyModel);
        //get user alcohol habits value
        userAlcoholHabits.getValue(userSurveyModel);
        //get user budget value
        userBudget.getValue(userSurveyModel);
        //get user university distance preference value
        userUniversityDistancePreference.getValue(userSurveyModel);
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.user.roommate.division.preferences.message"));
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.roommate.preference.details.message"));
        //get user roommate gender preference value
        userRoommateGenderPreference.getValue(userSurveyModel);
        //get user roommate food habits preference value
        userRoommateFoodHabitsPreference.getValue(userSurveyModel);
        //get user roommate smoking habits preference value
        userRoommateSmokingHabitsPreference.getValue(userSurveyModel);
        //get user roommate alcohol preference value
        userRoommateAlcoholHabitsPreference.getValue(userSurveyModel);
        //insert user personal details
        userSurveyDAO.insertUserPersonalDetails(userSurveyModel);
        //insert roommate preference details
        userSurveyDAO.insertRoommatePreferenceDetails(userSurveyModel);
        //update that survey is completed
        userSurveyDAO.updateSurveyTakenStatus();
        roomsicleCLI.printMessage(CommandLineInputProperties.getCommandLineInputPropertyValue("user.survey.profile.creation.message"));
        try {
            ClassInitializer.initializer().getUserLoginController().userLoginController();
        } catch (Exception e) {
            logger.error("invalid credentials");
        }
    }
}
