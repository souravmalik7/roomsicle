package database.ownersurveydao;

import models.ownersurveymodel.OwnerSurveyModel;

public interface IOwnerSurveyDAO {

    void insertOwnerSurveyDetails(OwnerSurveyModel ownerSurveyModel);

    void updateOwnerSurveyTakenStatus();
}
