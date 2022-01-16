package database.userregistrationdao;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.usermodel.IUsersModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRegistrationDAO implements IUserRegistrationDAO {
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    Connection connection = databaseConnection.getConnectionObject();
    Statement statement;
    public void userRegistration(IUsersModel usersModel){
        String query;
        String type;
        String emailId;
        String firstName;
        String lastName;
        long contactNumber;
        String password;

        type= usersModel.getType();
        emailId=usersModel.getEmailId();
        firstName=usersModel.getFirstName();
        lastName=usersModel.getLastName();
        contactNumber=usersModel.getContactNumber();
        password=usersModel.getPassword();
        try {
            statement = connection.createStatement();

            query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.registration.query")
                    .replace("users", type)
                    .replace("EmailId", emailId)
                    .replace("fName", firstName)
                    .replace("lName", lastName)
                    .replace("cno",  String.valueOf(contactNumber))
                    .replace("password00", password);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }

