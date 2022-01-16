package database.propertybiddingdao;

import models.biddingmodels.BiddingDetailsModel;

import java.util.ArrayList;

public interface IPropertyBidderDAO {

    ArrayList<BiddingDetailsModel> getPropertyBidDetails();

}
