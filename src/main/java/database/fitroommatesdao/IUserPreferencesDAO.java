package database.fitroommatesdao;

import models.fitroommatemodels.UserPreferencesModel;

import java.util.ArrayList;

public interface IUserPreferencesDAO {

    ArrayList<UserPreferencesModel> getUserPreferences();

}
