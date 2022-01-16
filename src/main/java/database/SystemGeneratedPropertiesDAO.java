package database;

import controller.ClassInitializer;
import controller.ControllerProperties;
import database.ownersurveydao.OwnerSurveyDAO;
import database.systemgeneratedpropertiesdao.ISystemGeneratedPropertiesDAO;
import models.systemgeneratedpropertiesmodel.SystemGeneratedPropertiesModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class SystemGeneratedPropertiesDAO implements ISystemGeneratedPropertiesDAO {

    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    static final Logger logger = LogManager.getLogger(OwnerSurveyDAO.class);
    Connection connection = databaseConnection.getConnectionObject();
    Statement statement;

    @Override
    public HashMap<String, Integer> getUserBudgetAndDistancePreference() {
        HashMap<String, Integer> userDetails = new HashMap<>();
        String query;
        String userEmailId;
        int userBudget;
        int userDalDistanceMin;
        int userDalDistanceMax;

        userEmailId = ControllerProperties.getControllerPropertyValue("user.logged.in.email.id");
        logger.info("userId: " + userEmailId);
        try {
            statement = connection.createStatement();
            query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.get.user.budget.Dalhousie.Distance.query")
                    .replace("userPersonalDetailsTableName", DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.user.personal.details.table.name"))
                    .replace("userEmailId", userEmailId);
            logger.info("Get User Budget And Distance Preference query: " + query);
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                userBudget = resultSet.getInt(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.user.budget.column.name"));
                userDalDistanceMin = resultSet.getInt(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.user.dalhousie.distance.minimum.value.column.name"));
                userDalDistanceMax = resultSet.getInt(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.user.dalhousie.distance.maximum.value.column.name"));

                userDetails.put(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.user.details.hashmap.budget.key"), userBudget);
                userDetails.put(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.user.details.hashmap.dalhousie.distance.minimum.key"), userDalDistanceMin);
                userDetails.put(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.user.details.hashmap.dalhousie.distance.maximum.key"), userDalDistanceMax);
            }
        } catch (SQLException e) {
            logger.error("Error while getting User Budget And Distance Preference");
        }
        return userDetails;
    }

    @Override
    public ArrayList<SystemGeneratedPropertiesModel> getSystemGeneratedPropertyDetails(HashMap<String, Integer> userDetails) {
        ArrayList<SystemGeneratedPropertiesModel> systemGeneratedPropertiesModelArrayList = new ArrayList<>();
        String query;
        int userBudget;
        int userDalDistanceMin;
        int userDalDistanceMax;

        userBudget = userDetails.get(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.user.details.hashmap.budget.key"));
        userDalDistanceMin = userDetails.get(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.user.details.hashmap.dalhousie.distance.minimum.key"));
        userDalDistanceMax = userDetails.get(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.user.details.hashmap.dalhousie.distance.maximum.key"));
        try {
            query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.get.system.generated.properties.query")
                    .replace("ownerTableName", DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.owner.personal.details.table.name"))
                    .replace("propertyDetailsTableName", DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.owner.property.details.table.name"))
                    .replace("userDalDistanceMin", String.valueOf(userDalDistanceMin))
                    .replace("userDalDistanceMax", String.valueOf(userDalDistanceMax))
                    .replace("userBudget", String.valueOf(userBudget));

            logger.info("Get System Generated Property Details query: " + query);
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                SystemGeneratedPropertiesModel systemGeneratedPropertiesModel = ClassInitializer.initializer().getNewSystemGeneratedPropertiesModel();
                systemGeneratedPropertiesModel.setFirstName(resultSet.getString(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.first.name.column.name")));
                systemGeneratedPropertiesModel.setLastName(resultSet.getString(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.last.name.column.name")));
                systemGeneratedPropertiesModel.setAddress(resultSet.getString(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.address.column.name")));
                systemGeneratedPropertiesModel.setContactNumber(resultSet.getLong(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.contact.number.column.name")));
                systemGeneratedPropertiesModel.setOwnerEmailId(resultSet.getString(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.email.id.column.name")));
                systemGeneratedPropertiesModel.setRent(resultSet.getInt(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.price.column.name")));
                systemGeneratedPropertiesModel.setDalhousieDistance(resultSet.getInt(DatabaseQueryProperties.getDatabaseQueryPropertyValue("system.generated.properties.dalhousie.distance.column.name")));
                systemGeneratedPropertiesModelArrayList.add(systemGeneratedPropertiesModel);
            }
        } catch (SQLException e) {
            logger.error("Error while getting System Generated Property Details");
        }
        return systemGeneratedPropertiesModelArrayList;
    }
}
