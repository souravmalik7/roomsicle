package database;

import database.DatabaseConnection;
import database.userlogindao.IUserLoginDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static controller.filterroommates.FilterRoommatesInputConstants.ONE;
import static controller.ownersurvey.OwnerSurveyConstants.TWO;

public class UserLoginDAO implements IUserLoginDAO {
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    Connection connection = databaseConnection.getConnectionObject();
    Statement statement;
    ResultSet getCredentials;

    public Map<String, String> getUserLoginAndPassword(String query){
        HashMap<String, String> getCredential = new HashMap<String, String>();
        try{
            statement = connection.createStatement();
            getCredentials=statement.executeQuery(query);
            while(getCredentials.next())
            {
                getCredential.put(getCredentials.getString(ONE),getCredentials.getString(TWO));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return getCredential;
    }

}
