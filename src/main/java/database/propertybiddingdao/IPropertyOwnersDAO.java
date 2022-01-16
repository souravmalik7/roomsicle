package database.propertybiddingdao;

import models.biddingmodels.PropertyOwnerModel;

import java.util.ArrayList;

public interface IPropertyOwnersDAO {

    ArrayList<PropertyOwnerModel> getPropertyOwnerDetails();

}
