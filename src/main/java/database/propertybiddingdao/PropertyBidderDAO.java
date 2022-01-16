package database.propertybiddingdao;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.biddingmodels.BiddingDetailsModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PropertyBidderDAO implements IPropertyBidderDAO{

    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    Connection connection;
    Statement statement;

    public ArrayList<BiddingDetailsModel> getPropertyBidDetails() {

        ArrayList<BiddingDetailsModel> listOfAllPropertyBiddings;

        listOfAllPropertyBiddings = new ArrayList<BiddingDetailsModel>();
        String query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("property.bidding.details.query");

        try(Connection conn = databaseConnection.getConnectionObject();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        ) {
            while(rs.next()){
                BiddingDetailsModel biddingDetailsModel = new BiddingDetailsModel();
                biddingDetailsModel.setUserEmailId(rs.getString(1));
                biddingDetailsModel.setPropertyId(rs.getString(2));
                biddingDetailsModel.setBid(Integer.parseInt(rs.getString(3)));
                listOfAllPropertyBiddings.add(biddingDetailsModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return listOfAllPropertyBiddings;
    }


}
