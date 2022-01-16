package database.fitroommatesdao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.fitroommatemodels.UserDetailsModel;

public class UserDetailsDAO implements IUserDetailsDAO {

	DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();

	public ArrayList<UserDetailsModel> getUserDetails() {

		ArrayList<UserDetailsModel> listOfAllUserDetails = new ArrayList<UserDetailsModel>();
		String query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.details.query");

		try(Connection conn = databaseConnection.getConnectionObject();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				) {		      
			while(rs.next()){
				UserDetailsModel userDetails = new UserDetailsModel();
				userDetails.setEmailId(rs.getString(1));
				userDetails.setFirstName(rs.getString(2));
				userDetails.setLastName(rs.getString(3));
				userDetails.setContactNumber(rs.getString(4));
				listOfAllUserDetails.add(userDetails);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} 
		return listOfAllUserDetails;
	}


}