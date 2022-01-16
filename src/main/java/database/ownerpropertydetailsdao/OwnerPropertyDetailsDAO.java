package database.ownerpropertydetailsdao;

import controller.ClassInitializer;
import controller.ControllerProperties;
import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.ownerpropertydetailsmodel.IOwnerPropertyDetailsModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static controller.filterroommates.FilterRoommatesInputConstants.*;

public class OwnerPropertyDetailsDAO  implements IOwnerPropertyDetailsDAO {
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();

    public ArrayList<IOwnerPropertyDetailsModel> getOwnersPropertyDetails() {
        IOwnerPropertyDetailsModel iOwnerPropertyDetailsModel= ClassInitializer.initializer().getIOwnerPropertyDetailsModel();
        ArrayList<IOwnerPropertyDetailsModel> listOfAllOwnersPropertyDetails = new ArrayList<>();
        String query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("owner.properties.query").
                replace("emailId", ControllerProperties.getControllerPropertyValue("user.logged.in.email.id"));

        try(Connection conn = databaseConnection.getConnectionObject();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
        ) {
            while(rs.next()){
                iOwnerPropertyDetailsModel.setOwnerId(rs.getString(ONE));
                iOwnerPropertyDetailsModel.setPrice(rs.getInt(TWO));
                iOwnerPropertyDetailsModel.setAddress(rs.getString(THREE));
                listOfAllOwnersPropertyDetails.add(iOwnerPropertyDetailsModel);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return listOfAllOwnersPropertyDetails;
    }

}
