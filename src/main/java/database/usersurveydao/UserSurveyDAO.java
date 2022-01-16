package database.usersurveydao;

import controller.ControllerProperties;
import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.usersurveymodel.UserSurveyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserSurveyDAO implements IUserSurveyDAO {

    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    static final Logger logger = LogManager.getLogger(UserSurveyDAO.class);
    Connection connection = databaseConnection.getConnectionObject();
    Statement statement;

    @Override
    public void insertUserPersonalDetails(UserSurveyModel userSurveyModel) {
        String query;
        String userId;
        String userGender;
        String userFoodHabits;
        String userSmokingHabits;
        String userAlcoholHabits;
        int userBudget;
        int userDalDistanceMin;
        int userDalDistanceMax;

        userId = userSurveyModel.getUserId();
        logger.info("userId: " + userId);
        userGender = userSurveyModel.getUserGender();
        logger.info("userGender: " + userGender);
        userFoodHabits = userSurveyModel.getUserFoodHabits();
        logger.info("userFoodHabits: " + userFoodHabits);
        userSmokingHabits = userSurveyModel.getUserSmokingHabits();
        logger.info("userSmokingHabits: " + userSmokingHabits);
        userAlcoholHabits = userSurveyModel.getUserAlcoholHabits();
        logger.info("userAlcoholHabits: " + userAlcoholHabits);
        userBudget = userSurveyModel.getUserBudget();
        logger.info("userBudget: " + userBudget);
        userDalDistanceMin = userSurveyModel.getUserDalDistanceMin();
        logger.info("userDalDistanceMin: " + userDalDistanceMin);
        userDalDistanceMax = userSurveyModel.getUserDalDistanceMax();
        logger.info("userDalDistanceMax: " + userDalDistanceMax);
        try {
            statement = connection.createStatement();

            query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.survey.insert.user.personal.details.query")
                    .replace("userPersonalDetailsTableName", DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.survey.user.personal.details.table.name"))
                    .replace("userId", userId).replace("userGender", userGender)
                    .replace("userFoodHabits", userFoodHabits)
                    .replace("userSmokingHabits", userSmokingHabits)
                    .replace("userAlcoholHabits", userAlcoholHabits)
                    .replace("userBudget", String.valueOf(userBudget))
                    .replace("userDalDistanceMin", String.valueOf(userDalDistanceMin))
                    .replace("userDalDistanceMax", String.valueOf(userDalDistanceMax));

            logger.info("query: " + query);
            statement.executeUpdate(query);
            logger.info("query executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertRoommatePreferenceDetails(UserSurveyModel userSurveyModel) {
        String query;
        String userId;
        String roommateGender;
        String roommateFoodHabits;
        String roommateSmokingHabits;
        String roommateAlcoholHabits;

        userId = userSurveyModel.getUserId();
        logger.info("userId: " + userId);
        roommateGender = userSurveyModel.getRoommateGender();
        logger.info("roommateGender: " + roommateGender);
        roommateFoodHabits = userSurveyModel.getRoommateFoodHabits();
        logger.info("roommateFoodHabits: " + roommateFoodHabits);
        roommateSmokingHabits = userSurveyModel.getRoommateSmokingHabits();
        logger.info("roommateSmokingHabits: " + roommateSmokingHabits);
        roommateAlcoholHabits = userSurveyModel.getRoommateAlcoholHabits();
        logger.info("roommateAlcoholHabits: " + roommateAlcoholHabits);
        try {
            statement = connection.createStatement();

            query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.survey.insert.roommate.preference.details.query")
                    .replace("roommatePreferenceDetailsTableName", DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.survey.roommate.preference.details.table.name"))
                    .replace("userId", userId).replace("roommateGender", roommateGender)
                    .replace("roommateFoodHabits", roommateFoodHabits)
                    .replace("roommateSmokingHabits", roommateSmokingHabits)
                    .replace("roommateAlcoholHabits", roommateAlcoholHabits);

            logger.info("query: " + query);
            statement.executeUpdate(query);
            logger.info("query executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSurveyTakenStatus() {
        String query;
        String userId;

        userId = ControllerProperties.getControllerPropertyValue("user.logged.in.email.id");
        try {
            statement = connection.createStatement();

            query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.survey.update.user.survey.taken.field.query")
                    .replace("userTableName", DatabaseQueryProperties.getDatabaseQueryPropertyValue("user.survey.user.details.table.name"))
                    .replace("userEmailID", userId);

            logger.info("query: " + query);
            statement.executeUpdate(query);
            logger.info("query executed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
