package models;

import java.util.ArrayList;
import java.util.List;

public class ExpenseAdditionModel implements IExpenseAdditionModel {
    private int expensePrice;
    private String userEmailId;
    private List<String> receiverEmailId = new ArrayList<>();
    private String description;
    private int groupId;

@Override
    public int getExpensePrice() {
        return expensePrice;
    }
@Override
    public void setExpensePrice(int expensePrice) {
        this.expensePrice = expensePrice;
    }

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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
this.description=description;
    }


}
