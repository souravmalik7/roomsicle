package database.fitroommatesdao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.fitroommatemodels.UserPreferencesModel;

public class UserPreferencesDAO implements IUserPreferencesDAO{

	DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
	Connection connection;
	Statement statement;

	public ArrayList<UserPreferencesModel> getUserPreferences() {

		ArrayList<UserPreferencesModel> listOfAllUserPreferences = new ArrayList<UserPreferencesModel>();
		String query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("bestfit.roommate.user.preferences.query");

		try(Connection conn = databaseConnection.getConnectionObject();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				) {		      
			while(rs.next()){

				UserPreferencesModel userPreferences = new UserPreferencesModel();
				userPreferences.setUserId(rs.getString(1));
				userPreferences.setRoommateGender(rs.getString(2));
				userPreferences.setRoommateFoodHabits(rs.getString(3));
				userPreferences.setRoommateSmoke(rs.getString(4));
				userPreferences.setRoommateAlcohol(rs.getString(5));
				listOfAllUserPreferences.add(userPreferences);

			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} 


		return listOfAllUserPreferences;

	}

}
