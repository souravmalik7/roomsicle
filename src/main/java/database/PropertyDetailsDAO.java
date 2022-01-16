package database;

import models.ownersurveymodel.OwnerSurveyModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PropertyDetailsDAO {

	DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();

	public ArrayList<OwnerSurveyModel> getPropertyDetails() {

		ArrayList<OwnerSurveyModel> listOfAllPropertyDetails = new ArrayList<OwnerSurveyModel>();
		String query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("property.details.query");

		try(Connection conn = databaseConnection.getConnectionObject();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
		) {
			while(rs.next()){
				OwnerSurveyModel propertyDetails = new OwnerSurveyModel();
				propertyDetails.setPropertyID(rs.getInt(1));
				propertyDetails.setOwnerID(rs.getString(2));
				propertyDetails.setAddress(rs.getString(3));
				propertyDetails.setNumberOfBedrooms(rs.getInt(4));
				propertyDetails.setUtilitiesProvided(rs.getBoolean(5));
				propertyDetails.setNumberOfVacancies(rs.getInt(6));
				propertyDetails.setDalhousieDistance(rs.getInt(7));
				propertyDetails.setGroceryStoreDistance(rs.getInt(8));
				propertyDetails.setDowntownDistance(rs.getInt(9));
				propertyDetails.setTheaterDistance(rs.getInt(10));
				propertyDetails.setPropertyPrice(rs.getInt(11));
				listOfAllPropertyDetails.add(propertyDetails);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		}
		return listOfAllPropertyDetails;
	}

}