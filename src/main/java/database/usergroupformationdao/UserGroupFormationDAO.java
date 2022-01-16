package database.usergroupformationdao;

import controller.ClassInitializer;
import controller.ControllerProperties;
import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import database.getgroupId.IGetGroupId;
import models.usergroupformationmodel.IUserGroupFormationModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserGroupFormationDAO implements IUserGroupFormationDAO {
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    Connection connection = databaseConnection.getConnectionObject();
    Statement statement;
    public void UserGroupFormationDAO(IUserGroupFormationModel userGroupFormationModel){
        String firstChoice;
        String secondChoice;
        String thirdChoice;
        String userId;
        int ID;
        String query;

        IGetGroupId getGroupDetails= ClassInitializer.initializer().getGetGroupDetails();
        firstChoice=userGroupFormationModel.getFirstEmailId();
        secondChoice=userGroupFormationModel.getSecondEmailId();
        thirdChoice= userGroupFormationModel.getThirdEmailId();
        userId= ControllerProperties.getControllerPropertyValue("user.logged.in.email.id");
        ID=getGroupDetails.getGroupId()+1;
        try {
            statement = connection.createStatement();
            query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("users.group.formation.query.name")
                    .replace("group_id_sample",String.valueOf(ID))
                    .replace("roommate1", userId)
                    .replace("roommate2", firstChoice)
                    .replace("roommate3", secondChoice)
                    .replace("roommate4", thirdChoice );
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
