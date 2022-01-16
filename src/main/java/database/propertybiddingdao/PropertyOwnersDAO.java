package database.propertybiddingdao;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.biddingmodels.PropertyOwnerModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PropertyOwnersDAO implements IPropertyOwnersDAO{

    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    Connection connection;
    Statement statement;
    
    public ArrayList<PropertyOwnerModel> getPropertyOwnerDetails() {

        ArrayList<PropertyOwnerModel> listOfAllPropertyOwner = new ArrayList<PropertyOwnerModel>();
        String query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("owner.details.query");

        try(Connection conn = databaseConnection.getConnectionObject();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        ) {
            while(rs.next()){
                PropertyOwnerModel propertyOwner = new PropertyOwnerModel();
                propertyOwner.setEmailId(rs.getString(1));
                propertyOwner.setFirstName(rs.getString(2));
                propertyOwner.setLastName(rs.getString(3));
                propertyOwner.setContactNumber(rs.getString(4));
                listOfAllPropertyOwner.add(propertyOwner);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return listOfAllPropertyOwner;
    }


}
