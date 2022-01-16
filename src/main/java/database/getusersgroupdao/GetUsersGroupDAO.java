package database.getusersgroupdao;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.IUsersGroupModel;
import models.UsersGroupModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static controller.filterroommates.FilterRoommatesInputConstants.*;
import static controller.usersurvey.UserSurveyConstants.FIVE;
import static controller.usersurvey.UserSurveyConstants.FOUR;

public class GetUsersGroupDAO implements IGetUserGroupDAO {
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    public ArrayList<IUsersGroupModel> getUsersGroup(){
        ArrayList<IUsersGroupModel> listOfAllUserDetails = new ArrayList<>();
        String query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.group.details.query");
        try(Connection conn = databaseConnection.getConnectionObject();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        ) {
            while(rs.next()){
                UsersGroupModel userGroup=new UsersGroupModel();
                userGroup.setGroupID(rs.getInt(ONE));
                userGroup.setFirstName(rs.getString(TWO));
                userGroup.setSecondName(rs.getString(THREE));
                userGroup.setThirdName(rs.getString(FOUR));
                userGroup.setFourthName(rs.getString(FIVE));
                listOfAllUserDetails.add(userGroup);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listOfAllUserDetails;
    }
}
