package database.expensesettleupdao;

import controller.ControllerProperties;
import database.DatabaseConnection;
import database.DatabaseQueryProperties;
import models.expensesettleup.IExpenseSettleUpModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ExpenseSettleUpDAO implements IExpenseSettleUpDAO {
    DatabaseConnection databaseConnection = DatabaseConnection.getDatabaseConnectionObject();

    @Override
    public void displayExpense(IExpenseSettleUpModel expenseSettleUpModel) {

        String userEmailId;
        List<String> receiverEmailId;

        userEmailId = ControllerProperties.getControllerPropertyValue("user.logged.in.email.id");
        expenseSettleUpModel.setUserEmailId(userEmailId);
        String getGroupInfoQuery = DatabaseQueryProperties.getDatabaseQueryPropertyValue("group.members.details.query")
                .replace("UserEmailId", userEmailId);
        try(Connection conn = databaseConnection.getConnectionObject();
            Statement stmt = conn.createStatement();
            ResultSet groupInfoRS = stmt.executeQuery(getGroupInfoQuery);
        ) {
            while(groupInfoRS.next()){
                expenseSettleUpModel.setGroupId(groupInfoRS.getInt(1));
                expenseSettleUpModel.setReceiverEmailId(groupInfoRS.getString(2));
                expenseSettleUpModel.setReceiverEmailId(groupInfoRS.getString(3));
                expenseSettleUpModel.setReceiverEmailId(groupInfoRS.getString(4));
                expenseSettleUpModel.setReceiverEmailId(groupInfoRS.getString(5));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        userEmailId = expenseSettleUpModel.getUserEmailId();
        receiverEmailId = expenseSettleUpModel.getReceiverEmailId();

        String finalUserEmailId = userEmailId;
        receiverEmailId.removeIf(id -> id.equals(finalUserEmailId));

        for (int i=0; i<receiverEmailId.size(); i++) {
            expenseSettleUpModel.setOweExpense(-expenseSettleUpModel.getOweExpense());
            expenseSettleUpModel.setBorrowExpense(-expenseSettleUpModel.getBorrowExpense());

            String oweExpenseDetailsQuery = DatabaseQueryProperties.getDatabaseQueryPropertyValue("expense.details.one.user.query")
                    .replace("ExpenserEmailId", userEmailId)
                    .replace("ReceiverEmailId", receiverEmailId.get(i));
            String borrowExpenseDetailsQuery = DatabaseQueryProperties.getDatabaseQueryPropertyValue("expense.details.one.user.query")
                    .replace("ExpenserEmailId", receiverEmailId.get(i))
                    .replace("ReceiverEmailId", userEmailId);

            try(Connection conn = databaseConnection.getConnectionObject();
                Statement stmt = conn.createStatement();
                ResultSet oweExpenseDetailsRS = stmt.executeQuery(oweExpenseDetailsQuery);

            ) {
                while(oweExpenseDetailsRS.next()){
                    expenseSettleUpModel.setOweExpense(oweExpenseDetailsRS.getInt(5));
                }
                System.out.println("\nOwe Expense for "+receiverEmailId.get(i)+" : " +expenseSettleUpModel.getOweExpense());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }

            try(Connection conn = databaseConnection.getConnectionObject();
                Statement stmt = conn.createStatement();
                ResultSet borrowExpenseDetailsRS = stmt.executeQuery(borrowExpenseDetailsQuery);

            ) {
                while(borrowExpenseDetailsRS.next()){
                    expenseSettleUpModel.setBorrowExpense(borrowExpenseDetailsRS.getInt(5));
                }
                System.out.println("Borrow Expense for "+receiverEmailId.get(i)+" : " +expenseSettleUpModel.getBorrowExpense());
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            System.out.println("Total Settle Up Expense: " +(expenseSettleUpModel.getOweExpense() - expenseSettleUpModel.getBorrowExpense()));
        }
    }
}
