package controller.expensemanagement;

import controller.ClassInitializer;
import controller.clicommentlist.IMakeCLICommentListController;
import database.expensesettleupdao.IExpenseSettleUpDAO;
import models.expensesettleup.IExpenseSettleUpModel;

public class ExpenseSettleUpController {

    public void userExpenseSettleUp() {
        IMakeCLICommentListController iMakeCLICommentListController= ClassInitializer.initializer().getIMakeCLICommentListController();
        IExpenseSettleUpDAO iExpenseSettleUpDAO=ClassInitializer.initializer().getIExpenseSettleUpDAO();
        IExpenseSettleUpModel iExpenseSettleUpModel=ClassInitializer.initializer().getIExpenseSettleUpModel();
        iMakeCLICommentListController.makeCLICommentListController("expense.decorator.message","expense.management.expense.settle.up.main.message","expense.decorator.message");
        iExpenseSettleUpDAO.displayExpense(iExpenseSettleUpModel);
    }
}
