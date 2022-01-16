package database;

import models.IExpenseAdditionModel;

public interface IExpenseAdditionDAO {
    void addExpense(IExpenseAdditionModel expenseAdditionModel);
}
