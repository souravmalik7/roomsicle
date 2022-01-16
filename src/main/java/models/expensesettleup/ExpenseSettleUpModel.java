package models.expensesettleup;

import java.util.ArrayList;
import java.util.List;

public class ExpenseSettleUpModel implements IExpenseSettleUpModel {
    private String userEmailId;
    private List<String> receiverEmailId = new ArrayList<>();
    private int groupId;
    private int oweExpense = 0;
    private int borrowExpense = 0;

    @Override
    public String getUserEmailId() {
        return userEmailId;
    }

    @Override
    public void setUserEmailId(String userEmailId) {
this.userEmailId = userEmailId;
    }

    @Override
    public List<String> getReceiverEmailId() {
        return receiverEmailId;
    }

    @Override
    public void setReceiverEmailId(String receiverEmailId) {
        this.receiverEmailId.add(receiverEmailId);
    }

    @Override
    public int getGroupId() {
        return groupId;
    }

    @Override
    public void setGroupId(int groupId) {
this.groupId=groupId;
    }

    @Override
    public int getOweExpense() {
        return oweExpense;
    }

    @Override
    public void setOweExpense(int oweExpense) {
this.oweExpense=this.oweExpense + oweExpense;
    }

    @Override
    public int getBorrowExpense() {
        return borrowExpense;
    }

    @Override
    public void setBorrowExpense(int borrowExpense) {
this.borrowExpense=this.borrowExpense + borrowExpense;
    }


}
