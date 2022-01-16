package database;

import controller.ControllerProperties;
import models.IExpenseAdditionModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExpenseAdditionDAO implements IExpenseAdditionDAO{
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();
    Connection connection = databaseConnection.getConnectionObject();

    Statement statement;

    @Override
    public void addExpense(IExpenseAdditionModel expenseAdditionModel) {
        String query;
        int expensePrice;
        String userEmailId;
        List<String> receiverEmailId;
        String description;
        int groupId;

        userEmailId = ControllerProperties.getControllerPropertyValue("user.logged.in.email.id");
        expenseAdditionModel.setUserEmailId(userEmailId);
        String getGroupInfoQuery = DatabaseQueryProperties.getDatabaseQueryPropertyValue("group.members.details.query")
                .replace("UserEmailId", userEmailId);
        try(Connection conn = databaseConnection.getConnectionObject();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getGroupInfoQuery);
        ) {
            while(rs.next()){
                expenseAdditionModel.setGroupId(rs.getInt(1));
                expenseAdditionModel.setReceiverEmailId(rs.getString(2));
                expenseAdditionModel.setReceiverEmailId(rs.getString(3));
                expenseAdditionModel.setReceiverEmailId(rs.getString(4));
                expenseAdditionModel.setReceiverEmailId(rs.getString(5));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        userEmailId = expenseAdditionModel.getUserEmailId();
        receiverEmailId = expenseAdditionModel.getReceiverEmailId();
        description = expenseAdditionModel.getDescription();
        groupId = expenseAdditionModel.getGroupId();
        expensePrice = expenseAdditionModel.getExpensePrice() / receiverEmailId.size();

        String finalUserEmailId = userEmailId;
        receiverEmailId.removeIf(id -> id.equals(finalUserEmailId));

        for (int i=0; i<receiverEmailId.size(); i++) {
            try {
                statement = connection.createStatement();
                query = DatabaseQueryProperties.getDatabaseQueryPropertyValue("expense.entry.query")
                        .replace("GroupId", String.valueOf(groupId))
                        .replace("UserEmailId", finalUserEmailId)
                        .replace("ReceiverEmailId", receiverEmailId.get(i))
                        .replace("Amount", String.valueOf(expensePrice))
                        .replace("Description", description);
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
