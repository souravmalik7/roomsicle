package database.ownerdetailsdao;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.ownerdetailsmodel.IOwnerDetailsModel;
import models.ownerdetailsmodel.OwnerDetailsModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static controller.filterroommates.FilterRoommatesInputConstants.*;
import static controller.usersurvey.UserSurveyConstants.FOUR;

public class OwnersDetailsDAO implements IOwnersDetailsDAO {
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    public ArrayList<IOwnerDetailsModel> getOwnersDetails() {
        ArrayList<IOwnerDetailsModel> listOfAllOwnersDetails = new ArrayList<>();
        String query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("owner.details.query");
        try(Connection conn = databaseConnection.getConnectionObject();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        ) {
            while(rs.next()){
                OwnerDetailsModel ownerDetails=new OwnerDetailsModel();
//                IOwnerDetailsModel iOwnerDetailsModel=ClassInitializer.initializer().getIOwnerDetailsModel();
                ownerDetails.setEmailId(rs.getString(ONE));
                ownerDetails.setFirstName(rs.getString(TWO));
                ownerDetails.setLastName(rs.getString(THREE));
                ownerDetails.setContactNumber(rs.getString(FOUR));
                listOfAllOwnersDetails.add(ownerDetails);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return listOfAllOwnersDetails;
    }
}
