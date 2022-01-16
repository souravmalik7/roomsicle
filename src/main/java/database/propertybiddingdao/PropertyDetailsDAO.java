package database.propertybiddingdao;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.biddingmodels.PropertyDetailsModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PropertyDetailsDAO implements IPropertyDetailsDAO{

    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    Connection connection;
    Statement statement;

    public ArrayList<PropertyDetailsModel> getPropertyDetails() {

        ArrayList<PropertyDetailsModel> listOfAllPropertyDetails = new ArrayList<PropertyDetailsModel>();
        String query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("property.details.query");

        try(Connection conn = databaseConnection.getConnectionObject();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        ) {
            while(rs.next()){
                PropertyDetailsModel propertyDetails = new PropertyDetailsModel();
                propertyDetails.setPropertyId(rs.getString(1));
                propertyDetails.setOwnerId(rs.getString(2));
                propertyDetails.setPropertyPrice(rs.getInt(10));
                listOfAllPropertyDetails.add(propertyDetails);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return listOfAllPropertyDetails;
    }

}