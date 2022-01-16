package database.propertybiddingdao;

import models.biddingmodels.BiddingDetailsModel;
import models.biddingmodels.IBiddingDetailsModel;

public interface IBiddingDAO {

    void enterBid(IBiddingDetailsModel biddingDetailsModel);

}
