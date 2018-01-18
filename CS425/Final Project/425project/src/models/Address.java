package models;

public class Address {
	protected int addrID;
	protected String street,deptNum,city,state,zipCode,country,phoneNumber;
	
	public Address(int addrID, String street, String deptNum, String city, String state, String zipCode, String country,
			String phoneNumber) {
		super();
		this.addrID = addrID;
		this.street = street;
		this.deptNum = deptNum;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
		this.phoneNumber = phoneNumber;
	}
	
	public Address(String street, String deptNum, String city, String state, String zipCode, String country,
			String phoneNumber) {
		this(-1,street,deptNum,city,state,zipCode,country,phoneNumber);
	}

	public int getAddrID() {
		return addrID;
	}
	public void setAddrID(int addrID) {
		this.addrID = addrID;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDeptNum() {
		return deptNum;
	}
	public void setDeptNum(String deptNum) {
		this.deptNum = deptNum;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
