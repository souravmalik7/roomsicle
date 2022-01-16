package models.usermodel;

public class UsersModel implements IUsersModel {
	private String emailId;
	private String firstName;
	private String lastName;
	private long contactNumber;
	private String password;
	private String type;

	public UsersModel() {}
	@Override
	public String getEmailId() {
		return emailId;
	}

	@Override
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public long getContactNumber() {
		return contactNumber;
	}

	@Override
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}


}