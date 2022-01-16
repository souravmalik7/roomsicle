package models.usergroupformationmodel;

import models.usergroupformationmodel.IUserGroupFormationModel;

public class UserGroupFormationModel implements IUserGroupFormationModel {
    private String firstEmailId;
    private String secondEmailId;
    private String thirdEmailId;

    public String getFirstEmailId() {
        return firstEmailId;
    }

    public void setFirstEmailId(String firstEmailId) {
        this.firstEmailId = firstEmailId;
    }

    public String getSecondEmailId() {
        return secondEmailId;
    }

    public void setSecondEmailId(String secondEmailId) {
        this.secondEmailId = secondEmailId;
    }

    public String getThirdEmailId() {
        return thirdEmailId;
    }

    public void setThirdEmailId(String thirdEmailId) {
        this.thirdEmailId = thirdEmailId;
    }
}
