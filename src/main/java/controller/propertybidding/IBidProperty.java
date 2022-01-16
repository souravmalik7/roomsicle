package controller.propertybidding;

import  Exception.InvalidBidException;
import database.propertybiddingdao.IPropertyBidderDAO;

public interface IBidProperty {

    void bidProperty() throws InvalidBidException;

    boolean getValidation();

}
