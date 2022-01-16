package models.expensesettleup;

import java.util.List;

public interface IExpenseSettleUpModel {
    String getUserEmailId();
    void setUserEmailId(String userEmailId);
    List<String> getReceiverEmailId();
    void setReceiverEmailId(String receiverEmailId);
    int getGroupId();
    void setGroupId(int groupId);
    int getOweExpense();
    void setOweExpense(int oweExpense);
    int getBorrowExpense();
    void setBorrowExpense(int borrowExpense);
}
