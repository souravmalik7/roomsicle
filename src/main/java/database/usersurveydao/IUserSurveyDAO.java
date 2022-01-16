package database.usersurveydao;

import models.usersurveymodel.UserSurveyModel;

public interface IUserSurveyDAO {

    void insertUserPersonalDetails(UserSurveyModel userSurveyModel);

    void insertRoommatePreferenceDetails(UserSurveyModel userSurveyModel);

    void updateSurveyTakenStatus();

}
