package models;

public class Flight {
	protected String aCode,fromIATA,toIATA;
	protected int flightNumber;
	public Flight(String aCode, String fromIATA, String toIATA, int flightumber) {
		super();
		this.aCode = aCode;
		this.fromIATA = fromIATA;
		this.toIATA = toIATA;
		this.flightNumber = flightumber;
	}
	public String getaCode() {
		return aCode;
	}
	public void setaCode(String aCode) {
		this.aCode = aCode;
	}
	public String getFromIATA() {
		return fromIATA;
	}
	public void setFromIATA(String fromIATA) {
		this.fromIATA = fromIATA;
	}
	public String getToIATA() {
		return toIATA;
	}
	public void setToIATA(String toIATA) {
		this.toIATA = toIATA;
	}
	public int getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}
	
	

}
