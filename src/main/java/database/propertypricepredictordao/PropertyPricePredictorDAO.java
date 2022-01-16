package database.propertypricepredictordao;

import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.ownersurveymodel.OwnerSurveyModel;
import models.propertypricecalculatormodel.PropertyPriceCalculatorModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PropertyPricePredictorDAO implements IPropertyPricePredictorDAO {
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    static final Logger logger = LogManager.getLogger(PropertyPricePredictorDAO.class);
    Connection connection;
    Statement statement;

    @Override
    public void insertPropertyPrice(OwnerSurveyModel ownerSurveyModel, PropertyPriceCalculatorModel propertyPriceCalculatorModel) {
        String query;
        int propertyPrice;
        int propertyID;

        propertyID = ownerSurveyModel.getPropertyID();
        logger.info("propertyID: " + propertyID);
        propertyPrice = propertyPriceCalculatorModel.getPropertyPrice();
        logger.info("propertyPrice: " + propertyPrice);

        try {
            connection = databaseConnection.getConnectionObject();
            statement = connection.createStatement();

            query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("property.price.predictor.update.property.price.query")
                    .replace("propertyDetailsTableName", DatabaseQueryProperties.getDatabaseQueryPropertyValue("property.price.predictor.table.name"))
                    .replace("propertyPrice", String.valueOf(propertyPrice))
                    .replace("propertyID", String.valueOf(propertyID));

            logger.info("query: " + query);
            statement.executeUpdate(query);
            logger.info("query executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
