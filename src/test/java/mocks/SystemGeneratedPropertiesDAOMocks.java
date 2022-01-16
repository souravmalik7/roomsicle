package mocks;

import models.systemgeneratedpropertiesmodel.SystemGeneratedPropertiesModel;

import java.util.ArrayList;

public class SystemGeneratedPropertiesDAOMocks {

    public ArrayList<SystemGeneratedPropertiesModel> getSystemGeneratedPropertyDetails() {
        ArrayList<SystemGeneratedPropertiesModel> systemGeneratedPropertiesModels = new ArrayList<>();
        SystemGeneratedPropertiesModel firstOwner = new SystemGeneratedPropertiesModel("jaimi@gmail.com", "jaimi", "sheta", "address1", 9898989898L, 1500, 3);
        SystemGeneratedPropertiesModel secondOwner = new SystemGeneratedPropertiesModel("rikin@gmail.com", "rikin", "patel", "address2", 6767676767L, 1300, 4);
        SystemGeneratedPropertiesModel thirdOwner = new SystemGeneratedPropertiesModel("hardik@gmail.com", "hardik", "kumar", "address3", 5656565656L, 1700, 2);
        systemGeneratedPropertiesModels.add(firstOwner);
        systemGeneratedPropertiesModels.add(secondOwner);
        systemGeneratedPropertiesModels.add(thirdOwner);
        return systemGeneratedPropertiesModels;
    }
}