package models.fitroommatemodels;

public interface IUserPreferenceModel {

    String getUserId();
    void setUserId(String userId);
    String getRoommateGender();
    void setRoommateGender(String roommateGender);
    String getRoommateFoodHabits();
    void setRoommateFoodHabits(String roommateFoodHabits);
    String getRoommateSmoke();
    void setRoommateSmoke(String roommateSmoke);
    String getRoommateAlcohol();
    void setRoommateAlcohol(String roommateAlcohol);

}
