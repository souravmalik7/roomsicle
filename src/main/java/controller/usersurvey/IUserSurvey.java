package controller.usersurvey;

import models.usersurveymodel.UserSurveyModel;

public interface IUserSurvey {

    void getValue(UserSurveyModel userSurveyModel);

    boolean validateValue(UserSurveyModel userSurveyModel);

    void setValue(UserSurveyModel userSurveyModel);
}
