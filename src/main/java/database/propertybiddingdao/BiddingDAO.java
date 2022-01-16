package database.propertybiddingdao;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.biddingmodels.BiddingDetailsModel;
import models.biddingmodels.IBiddingDetailsModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BiddingDAO implements IBiddingDAO{
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    Connection connection = databaseConnection.getConnectionObject();
    Statement statement;
    public void enterBid(IBiddingDetailsModel biddingDetailsModel){
        String query;
        String userEmailId;
        String propertyId;
        Integer bid;

        userEmailId = biddingDetailsModel.getUserEmailId();
        propertyId = biddingDetailsModel.getPropertyId();
        bid = biddingDetailsModel.getBid();

        try {
            statement = connection.createStatement();

            query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("bid.place.query")
                    .replace("UserEmailId", userEmailId)
                    .replace("PropertyId", propertyId)
                    .replace("Bid", bid.toString());
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

