package controller.ownersurvey;

import models.ownersurveymodel.OwnerSurveyModel;

public interface
IOwnerSurvey {

     void getValue(OwnerSurveyModel ownerSurveyModel);

     boolean validateValue(OwnerSurveyModel ownerSurveyModel);

     void setValue(OwnerSurveyModel ownerSurveyModel);

}
