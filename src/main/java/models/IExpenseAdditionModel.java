package models;

import java.util.List;

public interface IExpenseAdditionModel {
    int getExpensePrice();
    void setExpensePrice( int expensePrice );
    String getUserEmailId();
    void setUserEmailId(String userEmailId);
    List<String> getReceiverEmailId();
    void setReceiverEmailId(String receiverEmailId);
    int getGroupId();
    void setGroupId(int groupId);
    String getDescription();
    void setDescription(String description);
}
