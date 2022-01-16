package database.getgroupId;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static controller.filterroommates.FilterRoommatesInputConstants.ONE;
import static controller.usersurvey.UserSurveyConstants.ZERO;

public class GetGroupId implements IGetGroupId {
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    public int getGroupId(){
        int Id;
        String query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.group.id.max");
        Id=ZERO;
        try (Connection conn = databaseConnection.getConnectionObject();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        ) {
            while(rs.next()){
                Id=rs.getInt(ONE);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return Id;
    }
}
