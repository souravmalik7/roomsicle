package controller.propertypricepredictor;

import models.ownersurveymodel.OwnerSurveyModel;

public interface ICalculateIndividualFeaturePrice {

    int calculatePrice(OwnerSurveyModel ownerSurveyModel);

}
