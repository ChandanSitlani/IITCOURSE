package models;

public class Airport {
	protected String IATA, country,city,airportName,State;

	public Airport(String iATA, String country, String city, String state, String airportName) {
		super();
		IATA = iATA;
		this.country = country;
		this.city = city;
		this.airportName = airportName;
		State = state;
	}

	public String getIATA() {
		return IATA;
	}

	public void setIATA(String iATA) {
		IATA = iATA;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}
	
	
}
