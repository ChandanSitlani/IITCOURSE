package models;

public class UserInfo {
	protected int uid;
	protected String firstName,middleName,lastName,homeIATA;
	
	public UserInfo(int uid, String firstName, String middleName, String lastName, String homeIATA) {
		this.uid = uid;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.homeIATA = homeIATA;
	}
	
	public UserInfo(String firstName, String middleName, String lastName, String homeIATA) {
		this(-1,firstName,middleName,lastName,homeIATA);
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getHomeIATA() {
		return homeIATA;
	}
	public void setHomeIATA(String homeIATA) {
		this.homeIATA = homeIATA;
	}
	

}
