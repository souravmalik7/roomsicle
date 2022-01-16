package database.ownerpropertydetailsdao;

import models.ownerpropertydetailsmodel.IOwnerPropertyDetailsModel;

import java.util.ArrayList;

public interface IOwnerPropertyDetailsDAO {
    ArrayList<IOwnerPropertyDetailsModel> getOwnersPropertyDetails();
}
