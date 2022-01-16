package database.propertybiddingdao;

import models.biddingmodels.PropertyDetailsModel;

import java.util.ArrayList;

public interface IPropertyDetailsDAO {

    ArrayList<PropertyDetailsModel> getPropertyDetails();

}
