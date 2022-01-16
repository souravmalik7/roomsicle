package database.propertypricepredictordao;

import models.ownersurveymodel.OwnerSurveyModel;
import models.propertypricecalculatormodel.PropertyPriceCalculatorModel;

public interface IPropertyPricePredictorDAO {

    void insertPropertyPrice(OwnerSurveyModel ownerSurveyModel, PropertyPriceCalculatorModel propertyPriceCalculatorModel);

}
