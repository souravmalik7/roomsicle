package database.systemgeneratedpropertiesdao;

import models.systemgeneratedpropertiesmodel.SystemGeneratedPropertiesModel;

import java.util.ArrayList;
import java.util.HashMap;

public interface ISystemGeneratedPropertiesDAO {

    HashMap<String, Integer> getUserBudgetAndDistancePreference();

    ArrayList<SystemGeneratedPropertiesModel> getSystemGeneratedPropertyDetails(HashMap<String, Integer> userDetails);
}
