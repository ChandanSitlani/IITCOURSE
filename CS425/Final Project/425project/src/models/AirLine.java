package models;

public class AirLine {
	protected String acode,airLineName,Country;

	public AirLine(String acode, String airLineName, String country) {
		super();
		this.acode = acode;
		this.airLineName = airLineName;
		Country = country;
	}

	public String getAcode() {
		return acode;
	}

	public void setAcode(String acode) {
		this.acode = acode;
	}

	public String getAirLineName() {
		return airLineName;
	}

	public void setAirLineName(String airLineName) {
		this.airLineName = airLineName;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}
	
}
