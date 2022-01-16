package models.fitroommatemodels;

public class UserPreferencesModel implements IUserPreferenceModel{
	
	private String userId;
	private String roommateGender;
	private String roommateFoodHabits;
	private String roommateSmoke;
	private String roommateAlcohol;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoommateGender() {
		return roommateGender;
	}
	public void setRoommateGender(String roommateGender) {
		this.roommateGender = roommateGender;
	}
	public String getRoommateFoodHabits() {
		return roommateFoodHabits;
	}
	public void setRoommateFoodHabits(String roommateFoodHabits) {
		this.roommateFoodHabits = roommateFoodHabits;
	}
	public String getRoommateSmoke() {
		return roommateSmoke;
	}
	public void setRoommateSmoke(String roommateSmoke) {
		this.roommateSmoke = roommateSmoke;
	}
	public String getRoommateAlcohol() {
		return roommateAlcohol;
	}
	public void setRoommateAlcohol(String roommateAlcohol) {
		this.roommateAlcohol = roommateAlcohol;
	}
	
}
